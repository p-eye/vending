package com.example.vending.helper;

import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class ExcelHelper extends CommonHelper {

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
            if (!isTable(sheet))
                throw new IllegalAccessException("테이블이 올바르지 않습니다");
            return rowsToTable(sheet);
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

    private static List<String[]> rowsToTable(Sheet sheet) {

        List<String[]> table = new ArrayList<>();

        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            DataFormatter df = new DataFormatter();
            String[] str = new String[3];

            str[0] = df.formatCellValue(row.getCell(0));
            str[1] = df.formatCellValue(row.getCell(1));
            str[2] = df.formatCellValue(row.getCell(2));
            table.add(str);
        }
        return table;
    }
}
