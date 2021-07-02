package com.example.vending.service;

import com.example.vending.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class ExcelService {

    private ProductRepository productRepository;

    public void save(MultipartFile file){
        
    }
}
