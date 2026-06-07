package com.microservice.auth.dto;

import com.microservice.auth.entity.Role;

public record RegisterRequest(
        String email,
        String password,
        Role role,
        String firstName,
        String lastName,
        String phone,
        String nationalId
) {}
