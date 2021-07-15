package com.example.vending.repository;

import com.example.vending.entity.Product;
import com.example.vending.helper.write.TxtHelper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository2{

    private final EntityManager em;

    public ProductRepository2(EntityManager em) {
        this.em = em;
    }

    public Product save(Product product) {
        em.persist(product);
        TxtHelper.writeText(product);
        return product;
    }

    public List<Product> saveAll(List<Product> products) {

        List<Product> result = new ArrayList<Product>();

        for (Product product : products) {
            em.persist(product);
            result.add(product);
        }
        TxtHelper.writeText(products);
        return result;
    }
}
