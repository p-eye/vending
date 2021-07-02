package com.example.vending.helper;

import com.example.vending.entity.Product;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ExcelHelper extends CommonHelper {

    protected static List<Product> openFile(MultipartFile file) {

        System.out.println("setafssaD");
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheet("product"); // 여기서 null 처리
            int rowCnt = sheet.getPhysicalNumberOfRows(); // 공백 row 구분없이 전체 row 가져와서 쓰면 안됨
            System.out.println(rowCnt);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
