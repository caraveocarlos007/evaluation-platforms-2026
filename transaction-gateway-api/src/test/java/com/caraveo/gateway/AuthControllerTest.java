package com.caraveo.gateway;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.caraveo.gateway.application.dto.LoginRequest;
import com.caraveo.gateway.application.dto.LoginResponse;
import com.caraveo.gateway.application.usecase.LoginUseCase;
import com.caraveo.gateway.infrastructure.controller.AuthController;
import com.caraveo.gateway.infrastructure.security.JwtService;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    JwtService jwtService;

    @Mock
    LoginUseCase loginUseCase;

    @InjectMocks
    AuthController controller;

    @Test
    void loginShouldReturnToken() {

        LoginRequest request = new LoginRequest("admin", "admin123");

        when(loginUseCase.login(request)).thenReturn("fake-token");

        LoginResponse response = controller.login(request);

        assertNotNull(response.token());
    }
}