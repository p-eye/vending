package com.example.vending.repository;

import com.example.vending.common.helper.Helper;
import com.example.vending.dto.MailLog;
import com.example.vending.entity.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j
public class MailLogRepository {

    private final EntityManager em;
    private final Helper helper;

    public MailLog saveToAll(MailLog mailLog) {
        helper.writeExcel(mailLog);
        return null;
        /*
        // private save 가드 후 호출
        Product ret = save(product);
        if (ret == null)
            return null;
        return helper.writeAll(ret);

         */
    }


}
