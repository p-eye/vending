package com.example.vending.dto;

import com.example.vending.entity.Product;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductForm {

    private String name;
    private String title;
    private String content;

    public Product toEntity() {
        return new Product(null, name, title, content);
    }
}
