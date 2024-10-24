package com.project.traplaner.travelBoard.controller;

import com.project.traplaner.entity.TravelBoard;
import com.project.traplaner.mapper.TravelBoardMapper;
import com.project.traplaner.travelBoard.dto.SearchDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardDetailResponseDTO;
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

    // 목록 요청 (페이징과 검색 기능을 곁들인)
//    @GetMapping("")
//    public String list() {
//        System.out.println("목록");
//        return "travelBoard/list";
//    }
//    @GetMapping("")
//    public String list(Model model,
//                       @ModelAttribute("s") SearchDTO page) {
//        Map<String, Object> map = travelBoardService.getList(page);
//        model.addAttribute("bList", map.get("bList"));
//        model.addAttribute("maker", map.get("pm"));
//        return "travelBoard/list";
//    }

    @GetMapping("/list")
    public String travelBoardList(Model model) {
        List<TravelBoard> all = travelBoardMapper.findAll();
        model.addAttribute("travelBoardList", all);

        return "travelBoard/list";
    }


    @GetMapping("/info")
    public String detail() {
        System.out.println("글");


        return "travelBoard/info";
    }

//    @GetMapping("/info")
//    public String detail(@PathVariable int id,
//                         // model에 직접 데이터를 담는 로직을 생략할 수 있는 @ModelAttribute
//                         @ModelAttribute("s") SearchDTO page,
//                         Model model) {
//        id=1;
//        System.out.println("sasas");
//        TravelBoardDetailResponseDTO dto = travelBoardService.getDetail(id);
//        model.addAttribute("b", dto);
//        model.addAttribute("p", page);
//        return "travelBoard/info";
//    }



//    @GetMapping("/info")
//    public String info(Model model){
//        int id =1;
//
//        return
//    }



}
