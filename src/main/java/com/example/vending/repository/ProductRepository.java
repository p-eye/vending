package com.example.vending.repository;

import com.example.vending.common.helper.Helper;
import com.example.vending.entity.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j
public class ProductRepository {

    private final EntityManager em;
    private final Helper helper;

    public Product saveToAll(Product product) {
        // private save 가드 후 호출
        Product ret = save(product);
        if (ret == null)
            return null;
        boolean isWritten = helper.writeAll(ret);
        return isWritten ? ret : null;
    }

    public List<Product> saveToAll(List<Product> products) {
        List<Product> ret = save(products);
        if (ret == null)
            return null;
        boolean isWritten = helper.writeAll(ret);
        return isWritten ? ret : null;
    }

    private Product save(Product product){
        em.persist(product);
        return product;
    }

    private List<Product> save(List<Product> products){
        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            em.persist(product);
            result.add(product);
        }
        log.info("Saved products to DB");
        return result;
    }

}
