package com.example.vending.service;

import com.example.vending.dto.ProductForm;
import com.example.vending.entity.Product;
import com.example.vending.helper.FileHelper;
import com.example.vending.helper.UrlHelper;
import com.example.vending.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public void saveAll(List<Product> products){
        productRepository.saveAll(products);
    }

    public void saveToDb(ProductForm form) {
        productRepository.save(form.toEntity());
    }

    public void saveToDb(MultipartFile file) {
        List<Product> products = FileHelper.fileToProducts(file);
        if (products != null)
            productRepository.saveAll(products);
    }

    public void saveToDb(URL url) {
        Product product = UrlHelper.urlToProduct(url);
        if (product != null)
            productRepository.save(product);
    }
}
