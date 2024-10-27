package com.project.traplaner.travelplan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class travelController {
    @GetMapping("/travelplan")
    public String travelPlan() {
        return "travelplan/travelplan";
    }
    @PostMapping("/travelplan")
    public void travelSave(@RequestParam String travelplan) {
       //사용자 확인

       //json 파싱

       //서비스 로직으로 json 전달?

    }
}
