package com.example.vending.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @RequestMapping("/hello") // url 요청연결
    public String hello(Model model) {
        model.addAttribute("message", "this is coffee");
        return "index";
    }
}
