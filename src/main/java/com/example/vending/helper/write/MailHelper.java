package com.example.vending.helper.write;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Component
public class MailHelper {

    private JavaMailSender mailSender;
    private MimeMessage message;
    private MimeMessageHelper messageHelper;

    private final String to = "standingtherain15@gmail.com";
    private final String title = "[Springboot] Saved file list";
    private final String content = "this mail is sent automatically";

    // 생성자
    public MailHelper(JavaMailSender mailSender) throws
            MessagingException {
        this.mailSender = mailSender;
        message = mailSender.createMimeMessage();
        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    }

    // 받는 사람 이메일
    public void setTo(String email) throws MessagingException {
        messageHelper.setTo(email);
    }

    // 제목
    public void setSubject(String subject) throws MessagingException {
        messageHelper.setSubject(subject);
    }

    // 메일 내용
    public void setText(String text, boolean useHtml) throws MessagingException {
        messageHelper.setText(text, useHtml);
    }

    // 첨부 파일
    public void setAttach(String displayFileName, String pathToAttachment) throws MessagingException, IOException {
        File file = new ClassPathResource(pathToAttachment).getFile();
        FileSystemResource fsr = new FileSystemResource(file);

        messageHelper.addAttachment(displayFileName, fsr);
    }

    // 발송
    public void send() {
        try {
            mailSender.send(message);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMail(String fileName) {
        try {
            message = mailSender.createMimeMessage();
            messageHelper = new MimeMessageHelper(message, true, "UTF-8");


            MailHelper mailHandler = new MailHelper(mailSender);

            // 받는 사람
            mailHandler.setTo(to);
            // 제목
            mailHandler.setSubject(title);
            // HTML Layout
            String htmlContent = "<p>" + content +"<p>";
            mailHandler.setText(htmlContent, true);
            // 첨부 파일
            mailHandler.setAttach(fileName, "static/"+fileName);

            mailHandler.send();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}