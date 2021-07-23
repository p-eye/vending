package com.example.vending.common.helper.read;

import com.example.vending.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

enum Format {
    EXCEL, TXT
}

@Slf4j
public class FileHelper {

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
        List<String[]> table = null;
        int typeIdx = getFileType(file);
        if (typeIdx == EXCEL_IDX) {
            table = ExcelHelper.openFile(file);
        } else if (typeIdx == TXT_IDX) {
            table = TxtHelper.openFile(file);
        }
        if (table == null)
            return null;
        return tableToProducts(table);
    }

    private static List<Product> tableToProducts(List<String[]> table) {

        if (!isValidHeader(table.get(0)) || !isValidData(table))
            return null;

        List<Product> products = new ArrayList<>();
        table.stream().skip(1).forEach(str -> {
            Product product= new Product(null, str[0], str[1], str[2]);
            products.add(product);
        });
        log.info("Transformed file to List<Products>");
        return products;
    }

    private static boolean isValidHeader(String[] header) {

        if (header[0] == null || header[1] == null || header[2] == null
                || !"name".equals(header[0])
                || !"title".equals(header[1])
                || !"content".equals(header[2]))
            return false;
        return true;
    }

    private static boolean isValidData(List<String[]> table) {

        table.stream().skip(1).forEach(str -> {
            // 데이터타입이 전부 str인지
            String data0 = str[0];
            String data1 = str[1];
            String data2 = str[2];
        });
        return true;
    }

}
