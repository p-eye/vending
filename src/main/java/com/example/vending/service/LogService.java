package com.example.vending.service;

import com.example.vending.common.helper.SerDesHelper;
import com.example.vending.dto.MailLog;
import com.example.vending.exception.ApiRequestException;
import com.example.vending.repository.MailLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LogService {

    private final MailLogRepository mailLogRepository;
    private final SerDesHelper serDesHelper;

    public void saveLog(String mailJson) {
        MailLog mailLog = serDesHelper.deserialize(mailJson);
        if (mailLog == null) {
            throw new ApiRequestException("json 변환에서 에러가 생겼습니다");
        }
        mailLogRepository.saveToAll(mailLog);
        System.out.println(mailLog.toString());
    }

    /*
    public String correct() {
        String response =
                this.webClient.get().uri("/todos/1")
                        .retrieve().bodyToMono(String.class)
                        .block();
        return response;
    }
    */
}
