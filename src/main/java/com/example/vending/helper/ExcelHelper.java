package com.example.vending.helper;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {
    private static String EXCEL = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean isExcelFormat(MultipartFile file) {
        if (EXCEL.equals(file.getContentType()))
            return true;
        return false;
    }

    public static void openFile(MultipartFile file) {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheet("product"); // 여기서 null 처리
            int rowCnt = sheet.getPhysicalNumberOfRows(); // 공백 row 구분없이 전체 row 가져와서 쓰면 안됨
            System.out.println(rowCnt);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
