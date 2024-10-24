package com.project.traplaner.member.controller;

import com.project.traplaner.entity.Member;
import com.project.traplaner.entity.Travel;
import com.project.traplaner.member.service.MemberService;
import com.project.traplaner.member.dto.SignUpRequestDto;
import com.project.traplaner.mypage.service.MyPageBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    private final MyPageBoardService myPageBoardService;

    @GetMapping("/my-page")
    public String myPage(){

        return "member/my-page";
    }

    @GetMapping("/my-page/my-board")
    public String myBoard() {



        return "member/my-board";
    }

    // 마이페이지 나의 여행
    @GetMapping("/my-page/my-plan")
    public String myPlan(
             Model model) {

        int memberId = 2;
        System.out.println("asdasdasdasdas");
        List<Travel> list = myPageBoardService.getList(memberId);

        list.forEach(travel -> {
            System.out.println(travel.getId());
        });

        model.addAttribute("list", list);

        return "member/my-plan";
    }

}
