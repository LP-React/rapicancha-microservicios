package com.microservice.auth.auth.dto;

public record LoginRequest(
        String email,
        String password
) {}