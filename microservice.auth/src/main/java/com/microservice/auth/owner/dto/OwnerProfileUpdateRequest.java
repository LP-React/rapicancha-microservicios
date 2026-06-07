package com.microservice.auth.owner.dto;


public record OwnerProfileUpdateRequest(
        String firstName,
        String lastName,
        String nationalId,
        String phone
) {}
