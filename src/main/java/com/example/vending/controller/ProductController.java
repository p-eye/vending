package com.example.vending.controller;

import com.example.vending.dto.ProductForm;
import com.example.vending.entity.Product;
import com.example.vending.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class ProductController {

    private ProductRepository productRepository;

    @RequestMapping("/") // url 요청연결
    public String hello(Model model) {
        model.addAttribute("message", "this is coffee");
        return "index";
    }

    @GetMapping("/product/new")
    public String newProductForm() {
        return "product/new";
    }

    @PostMapping("/product/create")
    public String createProduct(ProductForm form) {
        // 1. DTO로 받아와서 Entity로 변환
        Product product = form.toEntity();

        // 2. Reposityroy에게 Entity를 DB에 저장하게 함
        productRepository.save(product);
        return "redirect:/product/new";
    }

}
