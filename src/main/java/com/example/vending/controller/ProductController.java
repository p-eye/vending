package com.example.vending.controller;

import com.example.vending.dto.ProductForm;
import com.example.vending.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URL;

@Controller
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @RequestMapping("/") // url 요청연결
    public String hello(Model model) {
        model.addAttribute("message", "this is coffee");
        return "index";
    }

    @GetMapping("/product/new")
    public String newProductForm() {
        return "product/new";
    }

    @PostMapping("/product/save/form")
    public String saveToDb(RedirectAttributes redirectAttributes, ProductForm form) {
        String mailJson = productService.saveToDb(form);
        redirectAttributes.addAttribute("mailLog", mailJson);
        return "redirect:/test";
    }

    @PostMapping("/product/save/file")
    public String saveToDb(RedirectAttributes redirectAttributes, MultipartFile file) {
        productService.saveToDb(file);
        return "redirect:/product/new";
    }

    @PostMapping("/product/save/url")
    public String saveToDb(URL url) {
        productService.saveToDb(url);
        return "redirect:/product/new";
    }

    @GetMapping("/product/saved")
    private String saved() {
        System.out.println("test");
        return "test";
    }
}
