package com.example.vending.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @RequestMapping("/hello")
    public String home(Model model) {
        model.addAttribute("message", "this is coffee");
        return "index.html";
    }
}
