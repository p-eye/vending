package com.example.vending.helper;

import com.example.vending.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class CommonHelper {

    private static String EXCEL = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static String TXT = "text/plain";
    private static int EXCEL_IDX = Format.EXCEL.ordinal();
    private static int TXT_IDX = Format.TXT.ordinal();
    protected static int columnCnt = 3;

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

    public static List<Product> tableToProducts(List<String[]> table) {
        List<Product> products = new ArrayList<>();
        for (String[] str : table) {
            Product product= new Product(null, str[0], str[1], str[2]);
            products.add(product);
        }
        return products;
    }
}
