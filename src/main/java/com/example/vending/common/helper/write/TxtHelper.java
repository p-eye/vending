package com.example.vending.common.helper.write;

import com.example.vending.entity.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class TxtHelper {

    private final String filePath = "/Users/p-eye/project/spring/vending/src/main/resources/static/";
    private final String dateFormat = "yyyyMMdd_HHmmss.SSS";

    public String writeText(List<Product> products) {
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

    public String writeText(Product product) {
        String fileNameTimeStamped = createTimeStampedFileName();
        File file = new File(filePath + fileNameTimeStamped);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String str = "name\ttitle\tcontent\n";
            writer.append(str);
            str = product.getName() + "\t" + product.getTitle() + "\t" + product.getContent() + "\n";
            writer.append(str);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
        log.info("Saved product to .txt");
        return file.getName();
    }

    private String createTimeStampedFileName() {
        String fileName = "productList";
        String fileType = ".txt";
        String timeStamp = new SimpleDateFormat(dateFormat).format(System.currentTimeMillis());

        return fileName + "_" + timeStamp + fileType;
    }
}
