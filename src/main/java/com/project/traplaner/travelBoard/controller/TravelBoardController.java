package com.project.traplaner.travelBoard.controller;

import com.project.traplaner.entity.Member;
import com.project.traplaner.member.dto.LoginUserResponseDTO;
import com.project.traplaner.travelBoard.dto.SearchDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardDetailResponseDTO;
import com.project.traplaner.travelBoard.service.TravelBoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/travelboard")
@RequiredArgsConstructor
@Slf4j
public class TravelBoardController {

    private final TravelBoardService travelBoardService;

    @GetMapping("/list")
    public String list(Model model, @ModelAttribute("s") SearchDTO page) {

        Map<String, Object> map = travelBoardService.getList(page);
        model.addAttribute("tbList", map.get("tbList"));
        model.addAttribute("maker", map.get("pm"));
        return "travelBoard/list";
    }

    @GetMapping("/info/{id}")
    public String info(Model model, @PathVariable("id") int id) {
        TravelBoardDetailResponseDTO one = travelBoardService.getOne(id);
        model.addAttribute("tOne", one);
        return "travelBoard/info";
    }

    @PostMapping("/{id}/toggle-like")
    @ResponseBody
    public ResponseEntity<Integer> toggleLike(@PathVariable int id, HttpSession session) {
        LoginUserResponseDTO dto = (LoginUserResponseDTO)session.getAttribute("login");
        log.info(String.valueOf(dto.getId()));
        int likeCount = travelBoardService.toggleLike(id, dto.getId());
        return ResponseEntity.ok(likeCount);  // 단일 정수 값 반환
    }


}
