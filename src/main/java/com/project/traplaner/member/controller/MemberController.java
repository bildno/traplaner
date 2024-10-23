package com.project.traplaner.member.controller;

import com.project.traplaner.entity.Member;
import com.project.traplaner.member.service.MemberService;
import com.project.traplaner.member.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    // 이메일, 아이디 중복 검사
    @PostMapping("/overlapping")
    @ResponseBody
    public ResponseEntity<?> check(
            @RequestParam String type,
            @RequestParam String keyword) {

        boolean flag = memberService.duplicateTest(type,keyword);
//        return ResponseEntity.ok()
//                .body(flag);
        return ResponseEntity.ok(flag);
    }
    //로그인 열기
    @GetMapping("/sign-in")
    public String index() {
        return "member/sign-in";
    }


    private String rootPath = "";
    private String appKey = "";

    // 로그인 화면 요청
    @GetMapping("/naver-sign-in")
    public void naverSignIn(){

        System.out.println("[dbg] naver-sign-in 진입!!!");
        log.info("naver-sing-in 진입");

    }


}
