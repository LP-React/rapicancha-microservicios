package com.microservice.auth.dto;

import com.microservice.auth.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private String phone;
    private String nationalId;
}
