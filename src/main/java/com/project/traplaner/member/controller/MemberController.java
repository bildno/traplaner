package com.project.traplaner.member.controller;
import com.project.traplaner.common.auth.JwtTokenProvider;
import com.project.traplaner.common.dto.CommonErrorDto;
import com.project.traplaner.common.dto.CommonResDto;
import com.project.traplaner.entity.Member;
import com.project.traplaner.member.dto.LoginRequestDto;
import com.project.traplaner.member.service.KakaoService;
import com.project.traplaner.member.service.MemberService;
import com.project.traplaner.member.dto.SignUpRequestDto;
import com.project.traplaner.util.FileUtils;
import com.project.traplaner.util.MailSenderService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MailSenderService mailSenderService;
    @Value("${file.upload.root-path}")
    private String rootPath;

    private final MemberService memberService;
    private final KakaoService kakaoService;
    private final JwtTokenProvider jwtTokenProvider;

    @Qualifier("member-template")
    private final RedisTemplate redisTemplate;

    //비밀 번호 변경 양식 열기
    @GetMapping("/pw-change")
    public String pwChange() {
        return "member/pw-change";
    }
    //비밀번호 변경로직
    @Transactional
    @PutMapping("pw-change")
    @ResponseBody
    public ResponseEntity<?> pwChange(@RequestBody Map<String, String> map)
    {
        String email = map.get("email");
        String password = map.get("password");
        String flag = memberService.changePassword(email, password)?"성공!":"실패!";
        log.info(email);
        log.info("변경 비밀번호: {}", password);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "비밀번호 변경 완료!",flag);
        return new ResponseEntity<>(commonResDto,HttpStatus.OK);
    }


    // 회원가입 양식 열기
    @GetMapping("/sign-up")
    public String join() {
        return "member/sign-up";
    }

    // 회원 가입 요청
    // 회원 가입 프로필 이미지 삽입 부분 바꿔야 할듯....
    @PostMapping("/sign-up")
    public ResponseEntity<?> sign_up(@Valid SignUpRequestDto dto) {
        log.info("member/sign-up: Post , dto: {}", dto.toString());
        dto.setLoginMethod(Member.LoginMethod.COMMON);

        // s:파일 업로드 ----------------------
//        String savePath = FileUtils.uploadFile(dto.getProfileImage(), rootPathProfile);
        String savePath = FileUtils.uploadFile(dto.getProfileImage(), rootPath);

//        log.info("rootPathProfile: {}", rootPathProfile);
        log.info("rootPathProfile: {}", rootPath);
        log.info("signup(): savePath {}", savePath);
        // e:파일 업로드 -------------------------

        Member member = memberService.join(dto, savePath);

        CommonResDto resDto =
                new CommonResDto(HttpStatus.CREATED, "member create 성공", member.getId());

        return new ResponseEntity<>(resDto, HttpStatus.CREATED);
    }

    // 이메일, 닉네임 중복 검사
    @PostMapping("/duplicateTest")
    @ResponseBody
    public ResponseEntity<?> check(@RequestBody Map<String, Object> params) {

        System.out.println("=====================================");

        log.info("type: {}", params.get("type"));
        log.info("keyword: {}", params.get("keyword"));

        boolean flag = memberService.duplicateTest((String) params.get("type"), (String) params.get("keyword"));
        return ResponseEntity.ok()
                .body(flag);
    }

    @GetMapping("/sign-in")
    public String index() {
        return "member/sign-in";
    }

    //로그인 요청
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody LoginRequestDto dto) {
        // email, password가 맞는 지 검증
        Member member = memberService.login(dto);

        // 회원 정보가 일치한다면, JWT를 클라이언트에게 발급해 주어야 한다. -> 로그인 유지를 위해!
        // Access Token을 생성해서 발급해 주겠다. -> 수명이 짧습니다.
        String token
                = jwtTokenProvider.createToken(member.getEmail());
        log.info("token: {}", token);

        // Refresh Token을 생성해 주겠다.
        // Access Token의 수명이 만료되었을 경우 Refresh Token을 확인해서 리프레시가 유효한 경우
        // 로그인 없이 Access Token을 재발급 해주는 용도로 사용.
        String refreshToken
                = jwtTokenProvider.createRefreshToken(member.getEmail());

        // refresh Token을 DB에 저장하자. -> redis에 저장.
        redisTemplate.opsForValue().set(member.getEmail(), refreshToken, 240, TimeUnit.HOURS);


        // 생성된 토큰 외에 추가로 전달할 정보가 있다면 Map을 사용하는 것이 좋습니다.
        Map<String, Object> logInfo = new HashMap<>();
        logInfo.put("token", token);
        logInfo.put("id", member.getId());

        CommonResDto resDto
                = new CommonResDto(HttpStatus.OK, "로그인 성공!", logInfo);
        return new ResponseEntity<>(resDto, HttpStatus.OK);
    }

    @Value("${sns.kakao.Client-Id}")
    private String kakaoClientId;
    @Value("${sns.kakao.logout-redirect}")
    private String kakaoLogoutRedirectUri;

    //    @Value("${sns.naver.Client-Id}")
//    private  String naverClientId;
//    @Value("${sns.naver.logout-redirect}")
//    private String naverLogoutRedirectUri;
//
    //네이버 로그인 화면 요청
    @GetMapping("/naver-sign-in")
    public void naverSignIn() {

        System.out.println("[dbg] naver-sign-in 진입!!!");
        log.info("naver-sing-in 진입");

    }

    // access token이 만료되어 새 토큰을 요청
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> id) {
        log.info("/members/refresh: POST, id: {}", id);
        Member member = memberService.findById(Long.parseLong(id.get("id")));
        log.info("조회된 Member: {}", member);

        // email로 redis를 조회해서 refresh token을 가져오자
        Object obj = redisTemplate.opsForValue().get(member.getEmail());
        log.info("레디스에서 조회한 데이터: {}", obj);
        if (obj == null) { // refresh token의 수명이 다됨.
            log.info("refresh 만료!");
            if(member.getLoginMethod() == Member.LoginMethod.KAKAO) {
                // 카카오 로그인 세션 종료시키기 어떻게하징........ㅠ
//                kakaoService.logout();
            }
            return new ResponseEntity<>(new CommonErrorDto(
                    HttpStatus.UNAUTHORIZED,
                    "EXPIRED_RT"
            ), HttpStatus.UNAUTHORIZED);
        }

        // 새로운 access token을 발급하자.
        String newAccessToken
                = jwtTokenProvider.createToken(member.getEmail());

        Map<String, Object> info = new HashMap<>();
        info.put("token", newAccessToken);
        CommonResDto resDto
                = new CommonResDto(HttpStatus.OK, "새 토큰 발급됨!", info);

        return new ResponseEntity<>(resDto, HttpStatus.OK);
    }

    @PostMapping("/email")
    @ResponseBody
    public ResponseEntity<?> mailCheck(@RequestBody String email) {
        log.info("이메일 인증 요청 들어옴!: {}", email);
        if(!memberService.duplicateTest("email", email)){
            log.info("존재하지 않는 회원");
            CommonResDto resDto
                    = new CommonResDto(HttpStatus.BAD_REQUEST,"존재하지 않는 회원입니다.", "");
            return new ResponseEntity<>(resDto, HttpStatus.BAD_REQUEST);
        };
        try {
            String authNum = mailSenderService.joinMail(email);
            CommonResDto resDto
                    = new CommonResDto(HttpStatus.OK,"올바른 이메일입니다.",authNum);
            return new ResponseEntity<>(resDto, HttpStatus.OK);
        } catch (MessagingException e) {
            CommonResDto resDto
                    = new CommonResDto(HttpStatus.BAD_REQUEST,"존재하지 않는 이메일 입니다.","");
            return new ResponseEntity<>(resDto, HttpStatus.BAD_REQUEST);
        }


    }

}