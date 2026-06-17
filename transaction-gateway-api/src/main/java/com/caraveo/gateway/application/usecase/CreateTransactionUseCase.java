package com.caraveo.gateway.application.usecase;

import org.springframework.stereotype.Service;

import com.caraveo.gateway.application.dto.CreateTransactionRequest;
import com.caraveo.gateway.application.dto.TransactionResponse;
import com.caraveo.gateway.infrastructure.client.TransactionApiClient;
import com.caraveo.gateway.infrastructure.security.CryptoService;

@Service
public class CreateTransactionUseCase {

    private final TransactionApiClient client;
    private final CryptoService cryptoService;

    public CreateTransactionUseCase(
            TransactionApiClient client,
            CryptoService cryptoService) {

        this.client = client;
        this.cryptoService = cryptoService;
    }

    public TransactionResponse execute(
            CreateTransactionRequest request) {

        System.out.println("====================================");
        System.out.println("SECRETO CIFRADO:");
        System.out.println(request.secreto());

        String decryptedSecret =
                cryptoService.decrypt(
                        request.secreto());

        System.out.println("SECRETO DESCIFRADO:");
        System.out.println(decryptedSecret);
        System.out.println("====================================");

        CreateTransactionRequest decryptedRequest =
                new CreateTransactionRequest(
                        request.operacion(),
                        request.importe(),
                        request.cliente(),
                        decryptedSecret);

        return client.create(decryptedRequest);
    }
}