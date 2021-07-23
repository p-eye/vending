package com.example.vending.common.helper.write;

import com.example.vending.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class WriteHelper {

    private TxtHelper txtHelper;
    private MailHelper mailHelper;

    public WriteHelper(JavaMailSender mailSender, TxtHelper txtHelper) {
        this.txtHelper = txtHelper;
        this.mailHelper = new MailHelper(mailSender);
    }

    public boolean writeAll(Product product) {
        String fileName = txtHelper.writeText(product);
        if (fileName == null)
            return false;
        return mailHelper.sendMail(fileName);
    }

    public boolean writeAll(List<Product> products) {
        String fileName = txtHelper.writeText(products);
        if (fileName == null)
            return false;
        return mailHelper.sendMail(fileName);
    }
}
