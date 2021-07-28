package com.example.vending.common.helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ExcelHelper {

    public List<String[]> openFile(MultipartFile file) {

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())){
            Sheet sheet = workbook.getSheet("product");
            if (sheet == null) {
                log.error("product 시트가 존재하지 않습니다");
                return null;
            }
            return sheetToTable(sheet);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private List<String[]> sheetToTable(Sheet sheet) {
        if (!isTable(sheet)) {
            log.error("테이블이 올바르지 않습니다");
            return null;
        }
        return rowsToTable(sheet);
    }

    private boolean isTable(Sheet sheet) {
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
            if (firstCellNum != 0 || lastCellNum != Define.columnCnt)
                return false;

            for (int c = firstCellNum; c < lastCellNum; c++) {
                Cell cell = row.getCell(c);
                if (cell == null)
                    return false;
            }
        }
        return true;
    }

    private List<String[]> rowsToTable(Sheet sheet) {

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
