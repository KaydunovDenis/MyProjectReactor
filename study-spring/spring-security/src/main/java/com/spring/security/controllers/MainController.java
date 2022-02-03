package com.spring.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping
    public String homePage() {
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageAuthenticated() {
        return "secured part of web service";
    }
}
