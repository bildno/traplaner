package com.project.traplaner.travelBoard.controller;

import com.project.traplaner.entity.TravelBoard;
import com.project.traplaner.mapper.TravelBoardMapper;
import com.project.traplaner.travelBoard.dto.SearchDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardDetailResponseDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardListResponseDTO;
import com.project.traplaner.travelBoard.service.TravelBoardService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        System.out.println("목록");
        return "travelBoard/list";
    }

    @GetMapping("/info/{id}")
    public String info(Model model, @PathVariable("id") int id, @ModelAttribute TravelBoardDetailResponseDTO travelBoardDetailResponseDTO) {
        TravelBoardDetailResponseDTO one = travelBoardService.getOne(id);
        model.addAttribute("tOne", one);
        System.out.println("글");
        return "travelBoard/info";
    }


}
