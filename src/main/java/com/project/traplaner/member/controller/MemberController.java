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

    @GetMapping("/pw-change")
    public String pwChange() {

        return "member/pw-change";
    }

    @GetMapping("/myPage")
    public String myPage(){

        return "member/my-page";
    }



    @GetMapping("/sign-up")
    public String join() {

        return "member/sign-up";
    }

    @PostMapping("/join")
    public String sing_up(SignUpRequestDto dto) {

        dto.setLoginMethod(Member.LoginMethod.COMMON);
        System.out.println(dto.getLoginMethod().toString());
        memberService.join(dto);
        return "member/sign-in";
    }

    @PostMapping("/overlapping")
    @ResponseBody
    public ResponseEntity<?> check(@RequestParam String email) {


        boolean flag = memberService.overlapping(email);
        return ResponseEntity.ok()
                .body(flag);
    }

    @GetMapping("/sign-in")
    public String index() {


        return "member/sign-in";
    }


//    sns.kakao.app-key=9d40d3ab8f7a7bfd11013922f7d0a3d6
//    sns.kakao.redirect-uri=http://localhost:8181/oauth/kakao
//    sns.kakao.logout-redirect=http://localhost:8181

    private String rootPath = "";

    private String appKey = "";


    // 로그인 화면 요청
    @GetMapping("/naver-sign-in")
    public void naverSignIn(){

        System.out.println("[dbg] naver-sign-in 진입!!!");
        log.info("naver-sing-in 진입");

    }


}
