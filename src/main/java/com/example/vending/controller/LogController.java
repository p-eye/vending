package com.example.vending.controller;

import com.example.vending.dto.EmpInfo;
import com.example.vending.service.LogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
@AllArgsConstructor
public class LogController {

    private LogService logService;

    @GetMapping("/test")
    public String TestWebClient(Model model){
        String response = logService.getFirstTodosTest();
        model.addAttribute("response",response);
        log.info(response);
        return "test";
    }
}
