package com.example.vending.helper;

import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {
    private static String EXCEL = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean isExcelFormat(MultipartFile file) {
        if (EXCEL.equals(file.getContentType()))
            return true;
        return false;
    }
}
