package com.example.vending.common.helper;

import com.example.vending.dto.MailLog;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ExcelHelper {

    private final String LogfilePath = "/Users/p-eye/project/spring/vending_log/MailLog.xlsx";
    private Workbook workbook;


    /*
    ** common
     */
    private InputStream fileToInputStream(File file, MultipartFile multipartFile) {
        try {
            if (file != null) {
                return new FileInputStream(file);
            } else {
                return multipartFile.getInputStream();
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            return null;
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private Sheet openFile(InputStream inputStream, String sheetName) {
        try {
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                log.error(sheetName + "시트가 존재하지 않습니다");
                return null;
            }
            return sheet;
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /*
     ** write
     */
    public void writeExcel(MailLog mailLog) {
        InputStream inputStream = fileToInputStream(new File(LogfilePath), null);
        Sheet sheet = openFile(inputStream, "MailLog");
        if (sheet == null) {
            return;
        }
        dtoToSheet(mailLog, sheet);
    }

    private void dtoToSheet(MailLog mailLog, Sheet sheet) {
        // 데이터 부분 생성
        int lastRowNum = sheet.getLastRowNum() + 1;
        for (String receiverMail : mailLog.getReceiverMail()) {
            Row row = sheet.createRow(lastRowNum);
            row.createCell(0).setCellValue(mailLog.getSenderMail());
            row.createCell(1).setCellValue(receiverMail);
            row.createCell(2).setCellValue(mailLog.getTitle());
            row.createCell(3).setCellValue(mailLog.getContent());
            row.createCell(4).setCellValue(mailLog.getAttachName());
            row.createCell(5).setCellValue(mailLog.getAttachPath());
            row.createCell(6).setCellValue(String.valueOf(mailLog.isStatus()));
            row.createCell(7).setCellValue(mailLog.getSendDate());
            lastRowNum++;
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(LogfilePath)){
            workbook.write(fileOutputStream);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /*
    ** read
     */
    public List<String[]> openFile(MultipartFile multipartFile) {
        InputStream inputStream = fileToInputStream(null, multipartFile);
        Sheet sheet = openFile(inputStream, "product");
        if (sheet == null) {
            return null;
        }
        return sheetToTable(sheet);
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
