package com.project.traplaner.mypage.controller;

import com.project.traplaner.mypage.dto.response.TravelListResponseDTO;
import com.project.traplaner.mypage.service.MyPageBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyPageBoardController {

    private final MyPageBoardService myPageBoardService;

    // 마이페이지 메인 (달력 있는 곳)
    // 달력에 일정 띄워주는 작업 해야댐
    // 계정관리 페이지 작성 필요
    @GetMapping("/my-page/{member_id}")
    public String myPage(@PathVariable int member_id, Model model) {


        return "member/my-page";
    }

    // 마이 페이지 내 게시물
    // db연동 아직 x
    @GetMapping("/my-page/mytravelboard/{member_id}")
    public String myBoard(@PathVariable int member_id, Model model) {




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

        return "main";
    }


}