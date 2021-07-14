package com.example.vending.helper.read;

import com.example.vending.entity.Product;

import java.net.URL;

public class UrlHelper {
    private static final String host = "product.com";
    private static final String path = "/create";

    public static Product urlToProduct(URL url) {
        if (!isValidHostAndPath(url) || !isValidParam(url))
            return paramToProduct(url);
        return null;
    }

    private static Product paramToProduct(URL url) {
        return null;
    }

    private static boolean isValidParam(URL url) {
        return true;

    }

    private static boolean isValidHostAndPath(URL url) {
        if (host.equals(url.getHost()) && path.equals(url.getPath()))
            return true;
        return false;
    }

}
