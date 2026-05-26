package com.microservice.auth.dto;

import lombok.*;

@Data
@AllArgsConstructor

public class LoginResponse {
    private Integer accountId;
    private String email;
    private String role;
    private String firstName;
    private String lastName;
    private Integer profileId;

    public LoginResponse(String loginOk) {
    }

    public LoginResponse() {

    }
}
