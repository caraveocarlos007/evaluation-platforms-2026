package com.caraveo.gateway.application.dto;

public record LoginRequest(
        String username,
        String password) {
}