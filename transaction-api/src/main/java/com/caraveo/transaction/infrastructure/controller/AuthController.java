package com.caraveo.transaction.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.caraveo.transaction.application.dto.LoginRequest;
import com.caraveo.transaction.application.dto.LoginResponse;
import com.caraveo.transaction.application.usecase.LoginUseCase;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest request) {

        boolean ok = loginUseCase.login(
                request.username(),
                request.password()
        );

        if (!ok) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return new LoginResponse(true);
    }
}