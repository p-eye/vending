package com.example.vending.helper;

import com.example.vending.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TxtHelper extends CommonHelper {

    protected static List<Product> openFile(MultipartFile file) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            return textToProducts(br);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private static List<Product> textToProducts(BufferedReader br) {
        try {
            if (!isTable(br))
                throw new IllegalAccessException("테이블이 올바르지 않습니다");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private static boolean isTable(BufferedReader br) {
        List<String> rows = new ArrayList<>();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        for (String row : rows) {
            String[] splited = row.split("\t");
            for (String elem : splited)
                if (elem.isEmpty())
                    return false;
            if (splited.length != columnCnt)
                return false;
        }
        return true;
    }
}

