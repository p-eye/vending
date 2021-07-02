package com.example.vending.controller;

import com.example.vending.dto.ProductForm;
import com.example.vending.entity.Product;
import com.example.vending.helper.ExcelHelper;
import com.example.vending.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
    public String saveForm(ProductForm form) {
        // 1. DTO로 받아와서 Entity로 변환
        Product product = form.toEntity();

        // 2. Reposityroy에게 Entity를 DB에 저장하게 함
        productRepository.save(product);
        return "redirect:/product/new";
    }

    @PostMapping("/product/create/file")
    public String saveFile(MultipartFile file) {
        if (ExcelHelper.isExcelFormat(file)) {
            try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
                Sheet sheet = workbook.getSheet("product"); // 여기서 null 처리
                int rowCnt = sheet.getPhysicalNumberOfRows(); // 공백 row 구분없이 전체 row 가져와서 쓰면 안됨
                System.out.println(rowCnt);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return "redirect:/product/new";
    }

}
