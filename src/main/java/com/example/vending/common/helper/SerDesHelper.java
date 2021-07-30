package com.example.vending.common.helper;

import com.example.vending.dto.MailLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SerDesHelper {

    public String serialize(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public MailLog deserialize(String mailJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(mailJson, MailLog.class);

        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
