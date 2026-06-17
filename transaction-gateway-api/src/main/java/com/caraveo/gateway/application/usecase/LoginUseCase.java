package com.caraveo.gateway.application.usecase;

import org.springframework.stereotype.Service;

import com.caraveo.gateway.application.dto.LoginRequest;
import com.caraveo.gateway.infrastructure.client.TransactionApiClient;
import com.caraveo.gateway.infrastructure.security.JwtService;

@Service
public class LoginUseCase {

    private final TransactionApiClient client;
    private final JwtService jwtService;

    public LoginUseCase(TransactionApiClient client,
                        JwtService jwtService) {
        this.client = client;
        this.jwtService = jwtService;
    }

    public String login(LoginRequest request) {

    	var response = client.login(request);

    	if (response == null || !response.success()) {
    	    throw new RuntimeException("Credenciales inválidas");
    	}

    	return jwtService.generateToken(request.username());
    }
}