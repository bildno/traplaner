package com.project.traplaner.member.controller;

import com.project.traplaner.entity.Member;
import com.project.traplaner.member.dto.LoginRequestDto;
import com.project.traplaner.member.service.LoginResult;
import com.project.traplaner.member.service.MemberService;
import com.project.traplaner.member.dto.SignUpRequestDto;
import com.project.traplaner.util.FileUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    @Value("${file.upload.root-path-profile}")
    private String rootPathProfile;

    @Value("${file.upload.root-path-travel}")
    private String rootPathTravel;

    private final MemberService memberService;

    //비밀 번호 변경 양식 열기
    @GetMapping("/pw-change")
    public String pwChange() {

        return "member/pw-change";
    }
    // 회원가입 양식 열기

    @GetMapping("/myPage")
    public String myPage(){

        return "member/my-page";
    }

    @GetMapping("/sign-up")
    public String join() {
        return "member/sign-up";
    }

    // 회원 가입 요청
    @PostMapping("/sign-up")
    public String sing_up(SignUpRequestDto dto) {
        dto.setLoginMethod(Member.LoginMethod.COMMON);
        System.out.println(dto.getLoginMethod().toString());

        // s:파일 업로드 ----------------------
        String savePath = FileUtils.uploadFile(dto.getProfileImage(), rootPathProfile);

        log.info("rootPathProfile: {}", rootPathProfile);
        log.info("signup(): savePath {}", savePath);
        // e:파일 업로드 -------------------------

//        memberService.join(dto);

        return "member/sign-in";
    }
    // 이메일, 닉네임 중복 검사
    @PostMapping("/duplicateTest")
    @ResponseBody
    public ResponseEntity<?> check(
            @RequestParam String type,
            @RequestParam String keyword) {

        boolean flag = memberService.duplicateTest(type,keyword);
        return ResponseEntity.ok()
                .body(flag);

    }
    //로그인 열기
    @GetMapping("/sign-in")
    public String index() {
        return "member/sign-in";
    }
    //로그인 요청
    @PostMapping("/sign-in")
    public String signIn(LoginRequestDto dto,
                         RedirectAttributes ra,
                         HttpServletResponse response,
                         HttpServletRequest request) {
        log.info("/member/sign-in: POST, dto: {}", dto);
        LoginResult result = memberService.authenticate(dto, request.getSession(), response);
        ra.addFlashAttribute("result", result);
        if (result == LoginResult.SUCCESS) { // 로그인 성공
            // 세션으로 로그인을 유지
            // 서비스에게 세션 객체와 이메일을 전달.
            memberService.maintainLoginState(request.getSession(), dto.getEmail());
            return "redirect:/";
        }
        return "redirect:/members/sign-in"; // 로그인 실패 시
    }



    private String rootPath = "";
    private String appKey = "";

    //네이버 로그인 화면 요청
    @GetMapping("/naver-sign-in")
    public void naverSignIn(){

        System.out.println("[dbg] naver-sign-in 진입!!!");
        log.info("naver-sing-in 진입");

    }


}
