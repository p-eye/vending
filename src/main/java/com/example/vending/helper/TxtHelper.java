package com.example.vending.helper;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TxtHelper extends FileHelper {

    private static void readLines(BufferedReader br, List<String> rows) {
        try {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    protected static List<String[]> openFile(MultipartFile file) {
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

    private static List<String[]> pageToList(List<String> rows) {
        try {
            if (!isTable(rows))
                throw new IllegalAccessException("테이블이 올바르지 않습니다");
            return rowsToTable(rows);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private static boolean isTable(List<String> rows) {
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

    private static List<String[]> rowsToTable(List<String> rows) {
        List<String[]> table = new ArrayList<>();

        for (String row : rows)
            table.add(row.split("\t"));
        return table;
    }

}

