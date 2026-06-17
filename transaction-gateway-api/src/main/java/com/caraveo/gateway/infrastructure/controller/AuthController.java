package com.caraveo.gateway.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.caraveo.gateway.application.dto.LoginRequest;
import com.caraveo.gateway.application.dto.LoginResponse;
import com.caraveo.gateway.application.usecase.LoginUseCase;

@RestController
@RequestMapping("/api/gateway/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest request) {

        String token = loginUseCase.login(request);

        return new LoginResponse(token);
    }
}