package com.example.vending.controller;

import com.example.vending.dto.ProductForm;
import com.example.vending.entity.Product;
import com.example.vending.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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

    @PostMapping("/product/create")
    public String saveForm(ProductForm form) {
        // 1. DTO로 받아와서 Entity로 변환
        Product product = form.toEntity();

        // 2. Reposityroy에게 Entity를 DB에 저장하게 함
        productService.save(product);
        return "redirect:/product/new";
    }

    @PostMapping("/product/create/file")
    public String saveFile(MultipartFile file) {
        Product product = new Product(null, "test", "tilte", "content");
        Product product2 = new Product(null, "asdf","asdf",",asdf");
        List<Product> products = new ArrayList<Product>();
        products.add(product);
        products.add(product2);
        productService.saveAll(products);
        /*
        List<Product> products = CommonHelper.openFile(file);
*/
        /*
        if (ExcelHelper.isExcelFormat(file)) {
            ExcelHelper.openFile(file);
        }
        */
        return "redirect:/product/new";
    }

}
