package com.example.vending.service;

import com.example.vending.dto.TestApi;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class LogService {

    private final WebClient webClient;

    public LogService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    public Mono<TestApi> test() {
        return this.webClient.get().uri("/todos/1")
                .retrieve().bodyToMono(TestApi.class);
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
