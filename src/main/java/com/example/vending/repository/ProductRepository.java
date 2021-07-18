package com.example.vending.repository;

import com.example.vending.entity.Product;
import com.example.vending.helper.write.MailHelper;
import com.example.vending.helper.write.TxtHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class ProductRepository2 {

    private final EntityManager em;
    private final MailHelper mailHelper;

    public Product save(Product product){
        em.persist(product);
        String fileName = TxtHelper.writeText(product);
        if (fileName != null) {
            mailHelper.sendMail(fileName);
            System.out.println("mail sending...");
        }
        return product;
    }

    public List<Product> saveAll(List<Product> products) {

        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            em.persist(product);
            result.add(product);
        }
        String fileName = TxtHelper.writeText(products);
        System.out.println("===================== " +fileName);
        return result;
    }
}
