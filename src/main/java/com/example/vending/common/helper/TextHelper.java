package com.example.vending.common.helper;

import com.example.vending.entity.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class TextHelper {

    private final String filePath = "/Users/p-eye/project/spring/vending/src/main/resources/static/";
    private final String dateFormat = "yyyyMMdd_HHmmss.SSS";

    /*
    write
     */
    public String writeText(Object object) {
        if (object instanceof ArrayList) {
            return writeListToText((List)object);
        } else {
            return writeListToText(Arrays.asList((Product)object));
        }
    }

    private String writeListToText(List<Product> products) {
        String fileNameTimeStamped = createTimeStampedFileName();
        File file = new File(filePath + fileNameTimeStamped);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            file.createNewFile();
            String str = "name\ttitle\tcontent\n";
            writer.append(str);
            for (Product product : products) {
                str = product.getName() + "\t" + product.getTitle() + "\t" + product.getContent() + "\n";
                writer.append(str);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
        log.info("Saved products to .txt");
        return file.getName();
    }

    private String createTimeStampedFileName() {
        String fileName = "productList";
        String fileType = ".txt";
        String timeStamp = new SimpleDateFormat(dateFormat).format(System.currentTimeMillis());

        return fileName + "_" + timeStamp + fileType;
    }

    /*
    read
    */
    private void readLines(BufferedReader br, List<String> rows) {
        try {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    protected List<String[]> openFile(MultipartFile file) {
        try {
            List<String> rows = new ArrayList<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            readLines(br, rows);
            br.close();
            return pageToList(rows);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private List<String[]> pageToList(List<String> rows) {
        try {
            if (!isTable(rows))
                throw new IllegalAccessException("테이블이 올바르지 않습니다");
            return rowsToTable(rows);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private boolean isTable(List<String> rows) {
        for (String row : rows) {
            String[] splited = row.split("\t");
            for (String elem : splited)
                if (elem.isEmpty())
                    return false;
            if (splited.length != Define.columnCnt)
                return false;
        }
        return true;
    }

    private List<String[]> rowsToTable(List<String> rows) {
        List<String[]> table = new ArrayList<>();

        for (String row : rows)
            table.add(row.split("\t"));
        return table;
    }
}
