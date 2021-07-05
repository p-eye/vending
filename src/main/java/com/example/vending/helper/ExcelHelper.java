package com.example.vending.helper;

import com.example.vending.entity.Product;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.STRING;

public class ExcelHelper extends CommonHelper {

    private static int rowStart = 1;

    protected static List<String[]> openFile(MultipartFile file) {

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())){
            Sheet sheet = workbook.getSheet("product");
            if (sheet == null)
                throw new IllegalAccessException("해당 시트가 존재하지 않습니다");
            return sheetToTable(sheet);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private static List<String[]> sheetToTable(Sheet sheet) {
        try {
            if (!isTable(sheet) || !isTableHeader(sheet))
                throw new IllegalAccessException("테이블이 올바르지 않습니다");
            if (!isValidElemType(sheet))
                throw new IllegalAccessException("데이터 형식이 올바르지 않습니다");
            return rowsToList(sheet);
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
            if (firstCellNum != 0 || lastCellNum != columnCnt)
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

    private static boolean isValidElemType(Sheet sheet) {

        for (int i = rowStart; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row.getCell(0).getCellType() != STRING
                || row.getCell(1).getCellType() != STRING
                || row.getCell(2).getCellType() != STRING) {
                System.out.println("row num : " + i + ", Invalid CellType");
                return false;
            }
        }
       return true;
    }

    private static List<String[]> rowsToList(Sheet sheet) {

        List<String[]> table = new ArrayList<>();

        for (int i = rowStart; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            String[] str = new String[3];

            str[0] = row.getCell(0).getStringCellValue();
            str[1] = row.getCell(1).getStringCellValue();
            str[2] = row.getCell(2).getStringCellValue();
            table.add(str);
        }
        return table;
    }
}
