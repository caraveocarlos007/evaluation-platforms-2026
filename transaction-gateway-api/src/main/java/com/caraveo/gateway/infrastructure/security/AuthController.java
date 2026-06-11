package com.caraveo.gateway.infrastructure.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.caraveo.gateway.application.dto.LoginRequest;
import com.caraveo.gateway.application.dto.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(
            JwtService jwtService) {

        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        if (!"admin".equals(request.username())
                || !"admin123".equals(request.password())) {

            throw new RuntimeException(
                    "Usuario o password incorrectos");
        }

        String token =
                jwtService.generateToken(
                        request.username());

        return new LoginResponse(token);
    }
}