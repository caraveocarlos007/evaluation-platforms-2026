package com.caraveo.gateway;

import com.caraveo.gateway.application.dto.LoginRequest;
import com.caraveo.gateway.application.dto.LoginResponse;
import com.caraveo.gateway.infrastructure.security.AuthController;
import com.caraveo.gateway.infrastructure.security.JwtService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthControllerTest {

    @Test
    void loginShouldReturnToken() {

        JwtService jwtService = new JwtService();

        AuthController controller = new AuthController(jwtService);

        LoginRequest request = new LoginRequest("admin", "admin123");

        LoginResponse response = controller.login(request);

        assertNotNull(response.token());
    }
}