package com.microservice.booking.dto;

import lombok.Data;

@Data
public class CheckInRequest {
    private String qrCode;
    private Integer ownerId;
}
