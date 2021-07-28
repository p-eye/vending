package com.example.vending.common.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Helper {
    private TextUtils txtHelper;
    private MailUtils mailHelper;

    public Helper(JavaMailSender mailSender, TextUtils txtHelper) {
        this.txtHelper = txtHelper;
        this.mailHelper = new MailUtils(mailSender);
    }

    public boolean writeAll(Object object) {
        String fileName = txtHelper.writeText(object);
        if (fileName == null)
            return false;
        return mailHelper.sendMail(fileName);
    }
}
