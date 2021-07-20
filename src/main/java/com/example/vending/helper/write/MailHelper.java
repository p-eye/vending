package com.example.vending.helper.write;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Slf4j
public class MailHelper {

    private JavaMailSender mailSender;
    private MimeMessage message;
    private MimeMessageHelper messageHelper;

    private final String to = "forsendingmymail@gmail.com";
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
    public void setAttach(String displayFileName, String path) throws MessagingException, IOException {
        messageHelper.addAttachment(displayFileName, new File(path));
    }

    // 발송
    public void send() {
        try {
            mailSender.send(message);
            log.info("Sended mail");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean sendMail(String fileName) {
        try {
            messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true, "UTF-8");
            MailHelper mailHelper = new MailHelper(mailSender);

            // 받는 사람
            mailHelper.setTo(to);
            // 제목
            mailHelper.setSubject(title);
            // HTML Layout
            String htmlContent = "<p>" + content +"<p>";
            mailHelper.setText(htmlContent, true);
            // 첨부 파일
            mailHelper.setAttach(fileName, "/Users/p-eye/project/spring/vending/src/main/resources/static/"+ fileName);
            mailHelper.send();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}