package com.example.vending.service;

import com.example.vending.dto.ProductForm;
import com.example.vending.entity.Product;
import com.example.vending.helper.read.FileHelper;
import com.example.vending.helper.read.UrlHelper;
import com.example.vending.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ProductService {

    private ProductRepository productRepository;

    public void saveToDb(ProductForm form) {
        save(form.toEntity());
    }

    public void saveToDb(MultipartFile file) {
        saveAll(FileHelper.fileToProducts(file));
    }

    public void saveToDb(URL url) {
        save(UrlHelper.urlToProduct(url));
    }

    public void saveAll(List<Product> products){
        if (products != null)
            productRepository.saveToAll(products);
    }

    public void save(Product product) {
        if (product != null)
            productRepository.saveToAll(product);
    }


}
