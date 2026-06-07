package com.microservice.auth.dto;


public record OwnerProfileUpdateRequest(
        String firstName,
        String lastName,
        String nationalId,
        String phone
) {}
