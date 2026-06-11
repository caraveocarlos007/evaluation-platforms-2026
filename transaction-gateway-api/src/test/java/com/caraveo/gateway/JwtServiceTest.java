package com.caraveo.gateway;

import com.caraveo.gateway.infrastructure.security.JwtService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void testGenerateAndValidateToken() {

        String token = jwtService.generateToken("admin");

        assertNotNull(token);
        assertTrue(jwtService.isValid(token));
    }

    @Test
    void testExtractUsername() {

        String token = jwtService.generateToken("admin");

        String username = jwtService.extractUsername(token);

        assertEquals("admin", username);
    }
}