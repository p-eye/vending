package com.example.vending.service;

import com.example.vending.entity.Product;
import com.example.vending.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public void saveAll(List<Product> products){
        productRepository.saveAll(products);
    }

    public void save(Product product) {
        productRepository.save(product);
    }
}
