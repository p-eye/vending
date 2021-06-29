package com.example.vending.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

}
