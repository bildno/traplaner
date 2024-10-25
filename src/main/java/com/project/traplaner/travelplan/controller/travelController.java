package com.project.traplaner.travelplan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class travelController {
    @GetMapping("/travelplan")
    public String travelplan() {
        return "travelplan/travelplan";
    }
}
