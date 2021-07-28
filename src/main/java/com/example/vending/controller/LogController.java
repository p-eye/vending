package com.example.vending.controller;

import com.example.vending.dto.TestApi;
import com.example.vending.service.LogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@AllArgsConstructor
public class LogController {

    private LogService logService;

    @GetMapping("/test")
    public Mono<TestApi> TestWebClient(Model model){
        return logService.test();
    }
}
