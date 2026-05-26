package com.microservice.auth.dto;

import lombok.Data;

@Data
public class CustomerProfileUpdateRequest {
    private String firstName;
    private String lastName;
    private String phone;
}
