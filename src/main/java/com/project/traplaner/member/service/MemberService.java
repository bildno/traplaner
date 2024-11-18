package com.project.traplaner.member.service;

import com.project.traplaner.entity.Member;
import com.project.traplaner.main.dto.MainTravelDto;
import com.project.traplaner.mapper.MemberMapper;
import com.project.traplaner.mapper.TravelMapper;
import com.project.traplaner.member.dto.LoginRequestDto;
import com.project.traplaner.member.dto.LoginUserResponseDTO;
import com.project.traplaner.member.dto.SignUpRequestDto;
import com.project.traplaner.member.repository.MemberRepository;
import com.project.traplaner.mypage.dto.ModifyMemberInfoDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;
    private final TravelMapper travelMapper;
    private final MemberRepository memberRepository;

    public Member join(SignUpRequestDto dto, String savePath){
        return memberRepository.save(dto.toEntity(encoder,savePath));
//        return memberMapper.save(dto.toEntity(encoder, savePath));
    }

    public boolean duplicateTest(String type, String keyword){
       return memberMapper.duplicateTest(type,keyword);
    }


    public void maintainLoginState(HttpSession session, String email) {
        Member foundMember = memberMapper.findOne(email);



        // 가입자 Travel 가져오기
        List<MainTravelDto> mainTravelDtoList = travelMapper.findByEmail(email);
        System.out.println("--------------------------------------------------");
        System.out.println(mainTravelDtoList.toString());
        log.info("mainTravelDtoList: {}", mainTravelDtoList.toString());
        System.out.println("--------------------------------------------------");


        // DB 데이터를 사용할 것만 정제
        LoginUserResponseDTO dto = LoginUserResponseDTO.builder()
                .id(foundMember.getId())
                .nickName(foundMember.getNickName())
                .email(foundMember.getEmail())
                .loginMethod(foundMember.getLoginMethod().getType())
                .profile(foundMember.getProfileImg())
                .build();

        log.info("로그인 사용자 dto: {}", dto);

        // 세션에 로그인 한 회원 정보를 저장
        session.setAttribute("login", dto);
        session.setAttribute("mainTravelDtoList", mainTravelDtoList);

        // 세션 수명 설정
        session.setMaxInactiveInterval(60 * 60); // 1시간
    }

    public void kakaoLogout(LoginUserResponseDTO dto, HttpSession session) {

        String requestUri = "https://kapi.kakao.com/v1/user/logout";

        String accessToken = (String) session.getAttribute("access_token");
        long kakaoAcount = (long) session.getAttribute("kakaoAccount");
        log.info("accessToken: {}", accessToken);
        log.info("kakaoAcount: {}", kakaoAcount);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("target_id_type", "user_id");
        params.add("target_id", kakaoAcount);

        session.invalidate();

        ResponseEntity<Map> responseEntity = new RestTemplate().exchange(
                requestUri,
                HttpMethod.POST,
                new HttpEntity<>(params, headers),
                Map.class);

    }

    public void naverLogout(LoginUserResponseDTO dto, HttpSession session) {

    }

    public boolean updateInfo(ModifyMemberInfoDTO dto){

        return memberMapper.update(dto.toEntity(encoder));
    }


    public boolean changePassword(String email, String password) {

        Member foundMember = memberRepository.findByEmail(email).orElseThrow(()->
                new EntityNotFoundException("비밀번호 변경 실패!")
                );
        foundMember.setPassword(encoder.encode(password));
        memberRepository.save(foundMember);
        return true;
    }

    public Member login(LoginRequestDto dto) {
        Member member  = memberRepository.findByEmail(dto.getEmail()).orElseThrow(() ->
                new EntityNotFoundException("User not found")
        );

        // 비밀번호 확인하기 (암호화 되어있으니 encoder에게 부탁)
        if (!encoder.matches(dto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return member;
    }

    public Member findById(long id) {
        return memberRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException("Member not found")
        );
    }
}
