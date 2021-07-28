package com.example.vending.common.helper;

import com.example.vending.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class Helper {
    private TextHelper txtHelper;
    private MailHelper mailHelper;
    private ExcelHelper excelHelper;

    public Helper(JavaMailSender mailSender, TextHelper txtHelper, ExcelHelper excelHelper) {
        this.txtHelper = txtHelper;
        this.mailHelper = new MailHelper(mailSender);
        this.excelHelper = excelHelper;
    }

    /*
    ** write
     */
    public boolean writeAll(Object object) {
        String fileName = txtHelper.writeText(object);
        if (fileName == null)
            return false;
        return mailHelper.sendMail(fileName);
    }

    /*
    ** read
     */

    public List<Product> fileToProducts(MultipartFile file)
    {
        List<String[]> table = null;
        int typeIdx = getFileType(file);
        if (typeIdx == Define.EXCEL_IDX) {
            table = excelHelper.openFile(file);
        } else if (typeIdx == Define.TXT_IDX) {
            table = txtHelper.openFile(file);
        }
        if (table == null)
            return null;
        return tableToProducts(table);
    }

    private int getFileType(MultipartFile file) {
        String type = file.getContentType();
        if (Define.EXCEL.equals(type))
            return Define.EXCEL_IDX;
        if (Define.TXT.equals(type))
            return Define.TXT_IDX;
        return -1;
    }

    private List<Product> tableToProducts(List<String[]> table) {

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

    private boolean isValidHeader(String[] header) {

        if (header[0] == null || header[1] == null || header[2] == null
                || !"name".equals(header[0])
                || !"title".equals(header[1])
                || !"content".equals(header[2]))
            return false;
        return true;
    }

    private boolean isValidData(List<String[]> table) {

        table.stream().skip(1).forEach(str -> {
            // 데이터타입이 전부 str인지
            String data0 = str[0];
            String data1 = str[1];
            String data2 = str[2];
        });
        return true;
    }

}
