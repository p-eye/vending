package com.example.vending.controller;

import com.example.vending.service.MailLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class MailLogController {

    private MailLogService logService;

    @GetMapping("/maillog/save")
    public String saveMailLog(@RequestParam("mailLog") String mailJson){
        logService.saveMailLog(mailJson);

        return null;
        //return logService.test();
    }

    @GetMapping("/maillogs")
    public String listMailLog() {
        logService.listMailLog();
        return null;
    }
}
