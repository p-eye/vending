package com.example.vending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class VendingApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendingApplication.class, args);
    }

}
