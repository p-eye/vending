package com.example.vending.service;

import com.example.vending.dto.EmpInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LogService {

    private final WebClient webClient;

    public LogService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    public String getFirstTodosTest() {
        String response =
                this.webClient.get().uri("/todos/1")
                        .retrieve().bodyToMono(String.class)
                        .block();
        return response;
    }
}
