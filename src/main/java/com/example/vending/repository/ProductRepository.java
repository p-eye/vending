package com.example.vending.repository;

import com.example.vending.entity.Product;
import com.example.vending.helper.write.MailHelper;
import com.example.vending.helper.write.TxtHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j
public class ProductRepository {

    private final EntityManager em;
    private final TxtHelper txthelper;
    private JavaMailSender mailSender;

    public Product saveToAll(Product product) {
        Product ret = save(product);
        if (ret == null)
            return null;
        String fileName = writeText(product);
        if (fileName == null)
            return null;
        boolean isMailSent = sendMail(fileName);
        if (isMailSent == false)
            return null;
        return ret;
    }

    public List<Product> saveToAll(List<Product> products) {
        List<Product> ret = save(products);
        String fileName = writeText(products);
        if (fileName == null)
            return null;
        boolean isMailSent = sendMail(fileName);
        if (isMailSent == false)
            return null;
        return ret;
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

    public String writeText(Product product) {
        return txthelper.writeText(product);
    }

    public String writeText(List<Product> products) {
        return txthelper.writeText(products);
    }

    public boolean sendMail(String fileName) {
        boolean isMailSent = false;
        try {
            if (fileName != null) {
                MailHelper mailHelper = new MailHelper(mailSender);
                isMailSent = mailHelper.sendMail(fileName);
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return isMailSent;
    }
}
