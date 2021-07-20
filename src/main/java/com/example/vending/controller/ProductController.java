package com.example.vending.controller;

import com.example.vending.dto.ProductForm;
import com.example.vending.entity.Product;
import com.example.vending.exception.ApiRequestException;
import com.example.vending.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

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
    public String saveToDb(ProductForm form) {
        productService.saveToDb(form);
        return "redirect:/product/new";
    }

    @PostMapping("/product/save/file")
    public String saveToDb(MultipartFile file) {
        List<Product> products = productService.saveToDb(file);
        return "redirect:/product/new";
    }

    @PostMapping("/product/save/url")
    public String saveToDb(URL url) {
        productService.saveToDb(url);
        return "redirect:/product/new";
    }

    @GetMapping("students")
    public List<Product> getAllProducts() {
        throw new ApiRequestException("Oops cannot get all products with custom exception");
    }

}
