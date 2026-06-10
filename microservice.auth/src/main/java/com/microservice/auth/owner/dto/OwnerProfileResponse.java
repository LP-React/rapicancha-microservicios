package com.microservice.auth.owner.dto;

public record OwnerProfileResponse(
        Integer accountId,
        String email,
        String firstName,
        String lastName,
        String nationalId,
        String phone
) {}
