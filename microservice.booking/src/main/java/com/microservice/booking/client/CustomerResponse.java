package com.microservice.booking.client;

import lombok.Data;

@Data
public class CustomerResponse {

    private Integer accountId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

}