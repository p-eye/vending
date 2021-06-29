package com.example.vending.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @RequestMapping("/home")
    public String home() {
        return "index.html";
    }
}
