package com.microservice.auth.dto;

import lombok.Data;

@Data
public class OwnerProfileUpdateRequest {
    private String firstName;
    private String lastName;
    private String nationalId;
    private String phone;
}
