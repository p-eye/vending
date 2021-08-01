package com.example.vending.service;

import com.example.vending.common.helper.Helper;
import com.example.vending.dto.MailLog;
import com.example.vending.exception.ApiRequestException;
import com.example.vending.repository.MailLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MailLogService {

    private final MailLogRepository mailLogRepository;
    private final Helper helper;

    public void saveMailLog(String mailJson) {
        MailLog mailLog = helper.deserialize(mailJson);
        if (mailLog == null) {
            throw new ApiRequestException("json 변환에서 에러가 생겼습니다");
        }
        mailLogRepository.saveToAll(mailLog);
    }

    public void listMailLog() {
        List<MailLog> mailLogList = mailLogRepository.listAll();
    }
}
