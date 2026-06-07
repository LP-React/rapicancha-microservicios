package com.microservice.auth.dto;

public record LoginRequest(
        String email,
        String password
) {}