package com.microservice.auth.dto;

import lombok.Data;

@Data
public class OwnerProfileResponse {
    private Integer accountId;
    private String email;
    private String firstName;
    private String lastName;
    private String nationalId;
    private String phone;
}
