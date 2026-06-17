package com.caraveo.gateway.infrastructure.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.caraveo.gateway.application.dto.CreateTransactionRequest;
import com.caraveo.gateway.application.dto.LoginApiResponse;
import com.caraveo.gateway.application.dto.LoginRequest;
import com.caraveo.gateway.application.dto.LoginResponse;
import com.caraveo.gateway.application.dto.PageResponse;
import com.caraveo.gateway.application.dto.TransactionResponse;
import com.caraveo.gateway.application.dto.UpdateTransactionStatusRequest;

@Component
public class TransactionApiClient {

    private final WebClient webClient;

    public TransactionApiClient(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://transaction-api:8081") // API 2
                .build();
    }

    // CREATE
    public TransactionResponse create(CreateTransactionRequest request) {
        return webClient
                .post()
                .uri("/api/transactions")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(TransactionResponse.class)
                .block();
    }

    // PATCH CANCELAR
    public TransactionResponse updateStatus(UpdateTransactionStatusRequest request) {
        return webClient
                .patch()
                .uri("/api/transactions/status")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(TransactionResponse.class)
                .block();
    }

    // GET PAGINADO
    public PageResponse getAll(int page, int size, String sortBy) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/transactions")
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .queryParam("sortBy", sortBy)
                        .build())
                .retrieve()
                .bodyToMono(PageResponse.class)
                .block();
    }
    
    // LOGGIN
    public LoginApiResponse login(LoginRequest request) {
        return webClient.post()
                .uri("/auth/login")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(LoginApiResponse.class)
                .block();
    }
}