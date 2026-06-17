package com.caraveo.transaction.application.dto;

public record LoginRequest(
        String username,
        String password
) {}