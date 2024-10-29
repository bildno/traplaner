package com.project.traplaner.mypage.controller;

import com.project.traplaner.entity.Member;
import com.project.traplaner.member.service.MemberService;
import com.project.traplaner.mypage.dto.ModifyMemberInfoDTO;
import com.project.traplaner.mypage.dto.response.FavoriteListResponseDTO;
import com.project.traplaner.mypage.dto.response.TravelBoardResponseDTO;
import com.project.traplaner.mypage.dto.response.TravelListResponseDTO;
import com.project.traplaner.mypage.service.MyPageBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageBoardService myPageBoardService;
    private final MemberService memberService;

    // 마이페이지 메인 (달력 있는 곳)
    // 달력에 일정 띄워주는 작업 해야댐
    // 계정관리 페이지 작성 필요
    @GetMapping("/my-page/{member_id}")
    public String myPage(@PathVariable int member_id, Model model) {

        List<TravelListResponseDTO> dtoList = myPageBoardService.getList(member_id);
        model.addAttribute("dtoList", dtoList);

        return "member/my-page";
    }

    // 마이 페이지 내 게시물
    // db연동 아직 x
    @GetMapping("/my-page/mytravelboard/{nickName}")
    public String myBoard(@PathVariable String nickName, Model model) {

        List<TravelBoardResponseDTO> dtoList = myPageBoardService.getTravelList(nickName);
        model.addAttribute("dtoList", dtoList);

        return "member/my-board";
    }


    // 마이페이지 나의 여행
    // 페이징기능 x, member_id 받아오는 처리 해야댐
    @GetMapping("/my-page/mytravel/{member_id}")
    public String myPlan(@PathVariable int member_id,
                         Model model) {

        System.out.println(member_id);
        List<TravelListResponseDTO> dtoList = myPageBoardService.getList(member_id);


        model.addAttribute("list", dtoList);

        return "member/my-plan";
    }

    @GetMapping("/tempMain")
    public String tempMain() {

        return "old-main";
    }

    @PostMapping("/my-page/shareIs/{boardId}")
    @ResponseBody
    public ResponseEntity<?> shareIs(@PathVariable int boardId) {
        myPageBoardService.updateShare(boardId);
        System.out.println("asdasd");

        return ResponseEntity.ok().body("success");
    }

    @PostMapping("/my-page/delete/{boardId}/{memberId}")
    @ResponseBody
    public ResponseEntity<?> deleteBoard(@PathVariable int boardId,
                                         @PathVariable int memberId,
                                         Model model) {

        List<TravelListResponseDTO> dtoList = myPageBoardService.getList(memberId);

        model.addAttribute("list", dtoList);

        myPageBoardService.deleteBoard(boardId);

        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/my-page/favorite/{memberId}")
    public String favorite(@PathVariable int memberId,
                           Model model) {
        List<FavoriteListResponseDTO> favorite = myPageBoardService.favorite(memberId);

        model.addAttribute("list", favorite);

        return "member/favorite";
    }

    @GetMapping("/my-page/pwChange")
    public String pwChange() {


        return "member/my-pw-change";
    }

    @PostMapping("/my-page/changeConfirm")
    public ResponseEntity<?> changeConfirm(@Validated @RequestBody ModifyMemberInfoDTO dto) {
        System.out.println("asdasd");

        System.out.println(dto.getNewPw() + "asaddasaswwww");
        boolean b = memberService.updateInfo(dto);


        if (b) {
            return ResponseEntity.ok().body("success");
        } else {
            return ResponseEntity.ok().body("fail");
        }


    }


    @PostMapping("/my-page/nickNameChk/{newNick}")
    public ResponseEntity<?> nickNameChk(@PathVariable String newNick) {
        String type = "nickname";

        boolean b = memberService.duplicateTest(type, newNick);
        System.out.println(b);
        if (!b) {
            return ResponseEntity.ok().body("success");
        } else {
            return ResponseEntity.badRequest().body("fail");
        }

    }

}



