package com.example.vending.controller;

import com.example.vending.dto.ProductForm;
import com.example.vending.entity.Product;
import com.example.vending.service.FileService;
import com.example.vending.service.ProductService;
import com.example.vending.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@Controller
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    private FileService fileService;
    private UrlService urlService;

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
    public String saveFormToDb(ProductForm form) {
        productService.saveToDb(form);
        return "redirect:/product/new";
    }

    @PostMapping("/product/save/file")
    public String saveFileToDb(MultipartFile file) {
        fileService.saveToDb(file);
        return "redirect:/product/new";
    }

    @PostMapping("/product/save/url")
    public String saveUrlToDb(URL url) {
        urlService.saveToDb(url);
        return "redirect:/product/new";
    }

}
