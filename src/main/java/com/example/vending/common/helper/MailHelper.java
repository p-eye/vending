package com.example.vending.common.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
public class MailHelper {

    private JavaMailSender mailSender;
    private MimeMessage message;
    private MimeMessageHelper messageHelper;

    private final String to = "forsendingmymail@gmail.com";
    private final String title = "[Springboot] Saved file list";
    private final String content = "this mail is sent automatically";

    public MailHelper(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        message = mailSender.createMimeMessage();
        try {
            messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }

    // 받는 사람 이메일
    private void setTo(String email) throws MessagingException {
        messageHelper.setTo(email);
    }

    // 제목
    private void setSubject(String subject) throws MessagingException {
        messageHelper.setSubject(subject);
    }

    // 메일 내용
    private void setText(String text, boolean useHtml) throws MessagingException {
        messageHelper.setText(text, useHtml);
    }

    // 첨부 파일
    private void setAttach(String displayFileName, String path) throws MessagingException, NullPointerException {
        messageHelper.addAttachment(displayFileName, new File(path));
    }

    // 발송
    private void send() throws MailAuthenticationException, MailSendException, MailException {
        mailSender.send(message);
        log.info("Sended mail");
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
            // 전송
            mailHelper.send();
            return true;
        }
        catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }
}