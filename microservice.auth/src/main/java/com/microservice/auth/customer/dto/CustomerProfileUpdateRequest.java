package com.microservice.auth.customer.dto;

public record CustomerProfileUpdateRequest(
        String firstName,
        String lastName,
        String phone
) {}
