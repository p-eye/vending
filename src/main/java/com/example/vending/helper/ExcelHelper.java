package com.example.vending.helper;

import com.example.vending.entity.Product;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper extends CommonHelper {

    protected static List<Product> openFile(MultipartFile file) {

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheet("product");
            if (sheet == null)
                throw new IllegalAccessException("해당 시트가 존재하지 않습니다");
            return sheetToProducts(sheet);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private static List<Product> sheetToProducts(Sheet sheet) {
        try {
            if (!isTable(sheet) || !isTableHeader(sheet))
                throw new IllegalAccessException("테이블이 올바르지 않습니다");
            return rowsToProducts(sheet);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private static boolean isTable(Sheet sheet) {
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        if (firstRowNum != 0)
            return false;

        for (int r = firstRowNum; r < lastRowNum; r++) {
            Row row = sheet.getRow(r);
            if (row == null)
                return false;

            int firstCellNum = row.getFirstCellNum();
            int lastCellNum = row.getLastCellNum();
            if (firstCellNum != 0 || lastCellNum != 3)
                return false;

            for (int c = firstCellNum; c < lastCellNum; c++) {
                Cell cell = row.getCell(c);
                if (cell == null)
                    return false;
            }
        }
        return true;
    }

    private static boolean isTableHeader(Sheet sheet) {

        Row headerRow = sheet.getRow(0);
        Cell header1 = headerRow.getCell(0);
        Cell header2 = headerRow.getCell(1);
        Cell header3 = headerRow.getCell(2);

        if (header1 == null || header2 == null || header3 == null
            || !"name".equals(header1.getStringCellValue())
            || !"title".equals(header2.getStringCellValue())
            || !"content".equals(header3.getStringCellValue()))
            return false;
        return true;
    }

    private static List<Product> rowsToProducts(Sheet sheet) {

        List<Product> products = new ArrayList<>();
        String name, title, content;
        int rowNum = 0;
        Iterator<Row> rows = sheet.iterator();
        while (rows.hasNext()) {
            Row currentRow = rows.next();
            System.out.println(currentRow.getRowNum());
            /*
            Row currentRow = rows.next();

            Iterator<Cell> cells = currentRow.iterator();
            while (cells.hasNext())
            {
                Cell currentCell = cells.next();
                currentCell.get
            }
            Product product = new Product();
            */

        }
        return null;
    }

}
