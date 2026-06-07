package com.microservice.auth.auth.dto;

import com.microservice.auth.account.enums.Role;

public record RegisterRequest(
        String email,
        String password,
        Role role,
        String firstName,
        String lastName,
        String phone,
        String nationalId
) {}
