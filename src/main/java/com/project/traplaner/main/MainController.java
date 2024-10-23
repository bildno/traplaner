package com.project.traplaner.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/home")
    public String homePage() {
        return "logout/logout";
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }
}
