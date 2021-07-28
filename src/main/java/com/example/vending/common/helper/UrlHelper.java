package com.example.vending.common.helper;

import com.example.vending.entity.Product;

import java.net.URL;

public class UrlHelper {
    private final String host = "product.com";
    private final String path = "/create";

    public Product urlToProduct(URL url) {
        if (!isValidHostAndPath(url) || !isValidParam(url))
            return paramToProduct(url);
        return null;
    }

    private Product paramToProduct(URL url) {
        return null;
    }

    private boolean isValidParam(URL url) {
        return true;

    }

    private boolean isValidHostAndPath(URL url) {
        if (host.equals(url.getHost()) && path.equals(url.getPath()))
            return true;
        return false;
    }

}
