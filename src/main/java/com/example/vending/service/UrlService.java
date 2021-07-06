package com.example.vending.service;

import com.example.vending.entity.Product;
import com.example.vending.helper.UrlHelper;
import com.example.vending.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

@Service
@AllArgsConstructor
public class UrlService {

    private ProductRepository productRepository;

    public void saveToDb(URL url) {
        Product product = UrlHelper.urlToProduct(url);
        if (product != null)
            productRepository.save(product);
    }
}
