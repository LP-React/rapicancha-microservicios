package com.microservice.court.dto;

import lombok.Data;

@Data
public class OwnerResponse {

    private Integer accountId;

    private String firstName;

    private String lastName;

}