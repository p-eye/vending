package com.example.vending.common.helper;

import com.example.vending.dto.MailLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@Slf4j
public class MailHelper {

    private JavaMailSender mailSender;
    private MimeMessage message;
    private MimeMessageHelper messageHelper;

    private final String from = "Your Site <forsendingmymail@gmail.com>";
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


    // 보내는 사람 이메일
    private void setFrom(String email, MailLog mailLog) throws MessagingException {
        messageHelper.setFrom(email);
        mailLog.setSenderMail(email);
    }

    // 받는 사람 이메일
    private void setTo(String email, MailLog mailLog) throws MessagingException {
        messageHelper.setTo(email);
        mailLog.getReceiverMail().add(email);
    }

    // 제목
    private void setSubject(String subject, MailLog mailLog) throws MessagingException {
        messageHelper.setSubject(subject);
        mailLog.setTitle(subject);
    }

    // 메일 내용
    private void setText(String text, boolean useHtml, MailLog mailLog) throws MessagingException {
        messageHelper.setText(text, useHtml);
        mailLog.setContent(text);
    }

    // 첨부 파일
    private void setAttach(String displayFileName, String path, MailLog mailLog) throws MessagingException, NullPointerException {
        messageHelper.addAttachment(displayFileName, new File(path));
        mailLog.setAttachName(displayFileName);
        mailLog.setAttachPath(path);
    }

    // 발송
    private void send(MailLog mailLog) throws MailAuthenticationException, MailSendException, MailException {
        mailSender.send(message);
        mailLog.setSendDate(new Date());
        log.info("Sended mail");
    }

    public MailLog sendMail(String fileName) {
        MailLog mailLog = new MailLog();
        try {
            messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true, "UTF-8");
            MailHelper mailHelper = new MailHelper(mailSender);

            // 보내는 사람
            mailHelper.setFrom(from, mailLog);
            // 받는 사람
            mailHelper.setTo(to, mailLog);
            // 제목
            mailHelper.setSubject(title, mailLog);
            // HTML Layout
            String htmlContent = "<p>" + content +"<p>";
            mailHelper.setText(htmlContent, true, mailLog);
            // 첨부 파일
            mailHelper.setAttach(fileName, "/Users/p-eye/project/spring/vending/src/main/resources/static/"+ fileName, mailLog);
            // 발송
            mailHelper.send(mailLog);
            mailLog.setStatus(true);
            return mailLog;
        }
        catch (Exception e){
            log.error(e.getMessage());
            mailLog.setStatus(false);
            return mailLog;
        }
    }
}