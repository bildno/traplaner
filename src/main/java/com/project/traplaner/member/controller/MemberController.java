package com.project.traplaner.member.controller;

import com.project.traplaner.entity.Member;
import com.project.traplaner.member.dto.LoginRequestDto;
import com.project.traplaner.member.service.LoginResult;
import com.project.traplaner.member.service.MemberService;
import com.project.traplaner.member.dto.SignUpRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //비밀 번호 변경 양식 열기
    @GetMapping("/pw-change")
    public String pwChange() {
        return "member/pw-change";
    }
    // 회원가입 양식 열기
    @GetMapping("/sign-up")
    public String join() {
        return "member/sign-up";
    }

    // 회원 가입 요청
    @PostMapping("/join")
    public String sing_up(SignUpRequestDto dto) {
        dto.setLoginMethod(Member.LoginMethod.COMMON);
        System.out.println(dto.getLoginMethod().toString());
        memberService.join(dto);
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
    //로그인 검증
    @PostMapping("/sign-in")
    public String signIn(LoginRequestDto dto,
                         RedirectAttributes ra,
                         HttpServletResponse response,
                         HttpServletRequest request) {

        // 자동 로그인 서비스를 추가하기 위해 세션과 응답객체도 함께 전달.
        LoginResult result = memberService.authenticate(dto, request.getSession(), response);
        // redirect에서 데이터를 일회성으로 전달할 때 사용하는 메서드.
        ra.addFlashAttribute("result", result);

        if (result == LoginResult.SUCCESS) { // 로그인 성공
            // 세션으로 로그인을 유지
            // 서비스에게 세션 객체와 아이디를 전달.
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
