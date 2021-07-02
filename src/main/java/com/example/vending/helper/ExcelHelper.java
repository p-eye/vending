package com.example.vending.helper;

import com.example.vending.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ExcelHelper extends CommonHelper {

    public static List<Product> openFile(MultipartFile file) {
        System.out.println("tesT");
        return null;
    }
/*
    public static void openFile(MultipartFile file) {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheet("product"); // 여기서 null 처리
            int rowCnt = sheet.getPhysicalNumberOfRows(); // 공백 row 구분없이 전체 row 가져와서 쓰면 안됨
            System.out.println(rowCnt);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

 */
}
