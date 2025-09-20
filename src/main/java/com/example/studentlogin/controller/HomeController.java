package com.example.studentlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping({"/", "/students"})
    public String students(Model model) {
        // simple hard-coded students list for demo
        List<Map<String, String>> students = List.of(
            Map.of("id","1", "name","Alice"),
            Map.of("id","2", "name","Bob"),
            Map.of("id","3", "name","Charlie")
        );
        model.addAttribute("students", students);
        return "students";
    }
}

