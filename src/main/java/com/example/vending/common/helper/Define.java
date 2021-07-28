package com.example.vending.common.helper;

enum Format {
    EXCEL, TXT
}

public class Define {
    public static final String EXCEL = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String TXT = "text/plain";
    public static int EXCEL_IDX = Format.EXCEL.ordinal();
    public static int TXT_IDX = Format.TXT.ordinal();
    public static int columnCnt = 3;
}
