package com.microservice.auth.dto;

import com.microservice.auth.entity.Role;

import java.time.LocalDateTime;

public record RegisterResponse(
        Integer accountId,
        String email,
        Role role,
        String status,
        LocalDateTime createdAt
) {}
