package com.microservice.auth.auth.dto;

import com.microservice.auth.account.enums.Role;

import java.time.LocalDateTime;

public record RegisterResponse(
        Integer accountId,
        String email,
        Role role,
        String status,
        LocalDateTime createdAt
) {}
