package com.example.vending.service;

import com.example.vending.dto.ProductForm;
import com.example.vending.entity.Product;
import com.example.vending.exception.ApiRequestException;
import com.example.vending.repository.ProductRepository;
import com.example.vending.common.helper.read.FileHelper;
import com.example.vending.common.helper.read.UrlHelper;
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

    public Product saveToDb(ProductForm form) {
        Product ret = productRepository.saveToAll(form.toEntity());
        if (ret == null) {
            throw new ApiRequestException("파일 쓰기에서 에러가 생겼습니다");
        }
        return ret;
    }

    public List<Product> saveToDb(MultipartFile file) {
        List<Product> products = FileHelper.fileToProducts(file);
        if (products == null) {
            throw new ApiRequestException("파일이 올바르지 않습니다");
        }
        List<Product> ret = productRepository.saveToAll(products);
        if (ret == null) {
            throw new ApiRequestException("파일 쓰기에서 에러가 생겼습니다");
        }
        return ret;
    }

    public Product saveToDb(URL url) {
        Product product = UrlHelper.urlToProduct(url);
        if (product == null) {
            throw new ApiRequestException("url이 올바르지 않습니다");
        }
        Product ret = productRepository.saveToAll(product);
        if (ret == null) {
            throw new ApiRequestException("파일 쓰기에서 에러가 생겼습니다");
        }
        return ret;
    }

}
