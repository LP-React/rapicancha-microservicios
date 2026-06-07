package com.microservice.auth.customer.dto;

public record CustomerProfileResponse(
        Integer accountId,
        String email,
        String firstName,
        String lastName,
        String phone
) {}
