package com.project.traplaner.travelBoard.controller;

import com.project.traplaner.mapper.TravelBoardMapper;
import com.project.traplaner.travelBoard.dto.SearchDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardDetailResponseDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardListResponseDTO;
import com.project.traplaner.travelBoard.service.TravelBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/travelboard")
@RequiredArgsConstructor
@Slf4j
public class TravelBoardController {

    private final TravelBoardService travelBoardService;
    private final TravelBoardMapper travelBoardMapper;

    @GetMapping("")
    public String travelBoardList(Model model, SearchDTO search) {
        List<TravelBoardListResponseDTO> all = travelBoardService.findAll();
        model.addAttribute("travelBoardList", all);

        return "travelBoard/list";
    }

    @GetMapping("/info")
    public String travelBoardDetail(Model model) {
        TravelBoardDetailResponseDTO one = travelBoardService.findOne();
        model.addAttribute("travelBoardDetail", model);

        return "travelBoard/info";
    }

}
