package com.example.vending.service;

import com.example.vending.common.helper.Helper;
import com.example.vending.common.helper.UrlHelper;
import com.example.vending.dto.MailLog;
import com.example.vending.dto.ProductForm;
import com.example.vending.entity.Product;
import com.example.vending.exception.ApiRequestException;
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
    private Helper fileHelper;
    private UrlHelper urlHelper;

    public MailLog saveToDb(ProductForm form) {
        MailLog mailLog = productRepository.saveToAll(form.toEntity());
        if (mailLog == null) {
            throw new ApiRequestException("파일 쓰기에서 에러가 생겼습니다");
        }
        return mailLog;
    }

    public List<Product> saveToDb(MultipartFile file) {

        List<Product> products = fileHelper.fileToProducts(file);
        if (products == null) {
            throw new ApiRequestException("파일이 올바르지 않습니다");
        }
        List<Product> ret = productRepository.saveToAll(products);
        if (ret == null) {
            throw new ApiRequestException("파일 쓰기에서 에러가 생겼습니다");
        }
        return null;
    }

    public Product saveToDb(URL url) {
        Product product = urlHelper.urlToProduct(url);
        if (product == null) {
            throw new ApiRequestException("url이 올바르지 않습니다");
        }
        MailLog ret = productRepository.saveToAll(product);
        if (ret == null) {
            throw new ApiRequestException("파일 쓰기에서 에러가 생겼습니다");
        }
        return null;
    }

}
