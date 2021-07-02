package com.example.vending.helper;

import com.example.vending.entity.Product;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;

public class ExcelHelper extends CommonHelper {

    protected static List<Product> openFile(MultipartFile file) {

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheet("product");
            if (sheet == null)
                throw new IllegalAccessException("해당 시트가 존재하지 않습니다");
            return checkColumns(sheet);

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private static List<Product> checkColumns(Sheet sheet) {
        try {
            Row headerRow = sheet.getRow(0);
            if (headerRow == null)
                throw new IllegalAccessException("해당 테이블이 존재하지 않습니다");
            if (headerRow.getFirstCellNum() != 0 && headerRow.getLastCellNum() != 3)
                throw new IllegalAccessException("테이블 column 개수가 올바르지 않습니다");
            Cell header1 = headerRow.getCell(0);
            Cell header2 = headerRow.getCell(1);
            Cell header3 = headerRow.getCell(2);
            if (header1 == null || header2 == null || header3 == null
                || !"name".equals(header1.getStringCellValue())
                || !"title".equals(header2.getStringCellValue())
                || !"content".equals(header3.getStringCellValue()))
                throw new IllegalAccessException("테이블 header 내용이 올바르지 않습니다");
            return rowsToProducts(sheet);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    private static List<Product> rowsToProducts(Sheet sheet) {
        return null;
    }
}
