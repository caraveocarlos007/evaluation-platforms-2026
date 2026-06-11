package com.caraveo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.caraveo.gateway"
        })
public class TransactionGatewayApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                TransactionGatewayApiApplication.class,
                args);
    }
}