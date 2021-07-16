package com.example.vending.service;

import com.example.vending.dto.Mail;
import com.example.vending.helper.write.MailHelper;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class MailService {
    private JavaMailSender mailSender;

    public void sendMail(Mail mailDto) {
        try {
            MailHelper mailHandler = new MailHelper(mailSender);

            // 받는 사람
            mailHandler.setTo(mailDto.getAddress());
            // 제목
            mailHandler.setSubject(mailDto.getTitle());
            // HTML Layout
            String htmlContent = "<p>" + mailDto.getMessage() +"<p>";
            mailHandler.setText(htmlContent, true);
            // 첨부 파일
            mailHandler.setAttach("saved.txt", "static/originTest.txt");

            mailHandler.send();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
