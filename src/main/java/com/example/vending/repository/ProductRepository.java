package com.example.vending.repository;

import com.example.vending.entity.Product;
import com.example.vending.common.helper.write.WriteHelper;
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
    private final WriteHelper writeHelper;

    public Product saveToAll(Product product) {
        Product ret = save(product);
        if (ret == null)
            return null;
        boolean isWritten = writeHelper.writeAll(ret);
        return isWritten ? ret : null;
    }

    public List<Product> saveToAll(List<Product> products) {
        List<Product> ret = save(products);
        if (ret == null)
            return null;
        boolean isWritten = writeHelper.writeAll(ret);
        return isWritten ? ret : null;
    }

    public Product save(Product product){
        em.persist(product);
        log.info("Saved product to DB");
        return product;
    }

    public List<Product> save(List<Product> products){
        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            em.persist(product);
            result.add(product);
        }
        log.info("Saved products to DB");
        return result;
    }

}
