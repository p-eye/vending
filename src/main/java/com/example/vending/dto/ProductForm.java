package com.example.vending.dto;

import com.example.vending.entity.Product;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductForm {

    private String title;

    public Product toEntity() {
        return new Product(null, title);
    }
}
