package com.microservice.auth.dto;

public record CustomerProfileUpdateRequest(
        String firstName,
        String lastName,
        String phone
) {}
