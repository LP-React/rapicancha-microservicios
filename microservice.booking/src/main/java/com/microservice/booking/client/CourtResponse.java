package com.microservice.booking.client;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourtResponse {

    private Integer idSportCourt;
    private String name;
    private String sportType;
    private BigDecimal rate;

}