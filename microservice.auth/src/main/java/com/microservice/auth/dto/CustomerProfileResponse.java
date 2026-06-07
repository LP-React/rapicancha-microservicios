package com.microservice.auth.dto;

public record CustomerProfileResponse(
        Integer accountId,
        String email,
        String firstName,
        String lastName,
        String phone
) {}
