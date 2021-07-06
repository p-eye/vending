package com.example.vending.service;

import com.example.vending.entity.Product;
import com.example.vending.helper.FileHelper;
import com.example.vending.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class FileService {

    private ProductRepository productRepository;

    public void saveToDb(MultipartFile file) {
        List<Product> products = FileHelper.fileToProducts(file);
        if (products != null)
            productRepository.saveAll(products);
    }
}
