package com.example.vending.helper.write;

import com.example.vending.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WriteHelper {

    private JavaMailSender mailSender;
    private MailHelper mailHelper;

    public WriteHelper(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        this.mailHelper = new MailHelper(mailSender);
    }


    public boolean writeAll(Product product) {
        TxtHelper txtHelper = new TxtHelper();
        String fileName = txtHelper.writeText(product);
        if (fileName == null)
            return false;
        boolean isMailSent = sendMail(fileName);
        return isMailSent;
    }

    public boolean sendMail(String fileName) {
        boolean isMailSent = false;
        try {
            if (fileName != null) {
                isMailSent = mailHelper.sendMail(fileName);
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return isMailSent;
    }
}
