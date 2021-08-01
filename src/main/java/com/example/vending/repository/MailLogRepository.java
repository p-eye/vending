package com.example.vending.repository;

import com.example.vending.common.helper.Helper;
import com.example.vending.dto.MailLog;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j
public class MailLogRepository {

    private final Helper helper;
    public MailLog saveToAll(MailLog mailLog) {
        log.info(mailLog.toString());
        helper.writeExcel(mailLog);
        return null;
    }

    public List<MailLog> listAll() {
        return null;
    }
}
