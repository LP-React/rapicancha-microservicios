package com.microservice.auth.auth.dto;

public record LoginResponse(
        Integer accountId,
        String email,
        String role,
        String firstName,
        String lastName,
        Integer profileId,
        String token
) {}
