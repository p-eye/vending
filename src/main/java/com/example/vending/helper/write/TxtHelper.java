package com.example.vending.helper.write;

import com.example.vending.entity.Product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TxtHelper {

    public static void writeText(List<Product> products) {
        String fileNameTimeStamped = createTimeStampedFileName();
        File file = new File("/Users/p-eye/project/spring/vending/" + fileNameTimeStamped);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String str = "name\ttitle\tcontent\n";
            writer.append(str);
            for (Product product : products) {
                str = product.getName() + "\t" + product.getTitle() + "\t" + product.getContent() + "\n";
                writer.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeText(Product product) {
        String fileNameTimeStamped = createTimeStampedFileName();
        File file = new File("/Users/p-eye/project/spring/vending/" + fileNameTimeStamped);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String str = "name\ttitle\tcontent\n";
            writer.append(str);
            str = product.getName() + "\t" + product.getTitle() + "\t" + product.getContent() + "\n";
            writer.append(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String createTimeStampedFileName() {
        String fileName = "productList";
        String fileType = "txt";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss.SSS").format(System.currentTimeMillis());

        return fileName + "_" + timeStamp + "." + fileType;
    }
}