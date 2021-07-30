package com.example.vending.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MailLog {
    private String senderMail;
    private List<String> receiverMail;
    private String title;
    private String content;
    private String attachName;
    private String attachPath;
    private boolean status;
    private Date sendDate;

    public MailLog() {
        receiverMail = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "MailLog{" +
                "senderMail='" + senderMail + '\'' +
                ", receiverMail=" + receiverMail +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", attachName='" + attachName + '\'' +
                ", attachPath='" + attachPath + '\'' +
                ", status=" + status +
                ", sendDate=" + sendDate +
                '}';
    }
}

