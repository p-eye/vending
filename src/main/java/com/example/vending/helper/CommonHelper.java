package com.example.vending.helper;

import com.example.vending.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public abstract class CommonHelper {

    private static String EXCEL = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static String TXT = "text/plain";
    private static int EXCEL_IDX = Format.EXCEL.ordinal();
    private static int TXT_IDX = Format.TXT.ordinal();

    private static int getFileType(MultipartFile file) {

        String type = file.getContentType();

        if (EXCEL.equals(type))
            return EXCEL_IDX;
        if (TXT.equals(type))
            return TXT_IDX;
        return -1;
    }

    public static List<Product> fileToProducts(MultipartFile file)
    {
        int typeIdx = getFileType(file);

        if (typeIdx == EXCEL_IDX)
            return ExcelHelper.openFile(file);
        if (typeIdx == TXT_IDX)
            return TxtHelper.openFile(file);
        return null;
    }
}
