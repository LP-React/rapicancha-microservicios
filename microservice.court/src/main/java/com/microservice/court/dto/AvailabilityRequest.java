package com.microservice.court.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class AvailabilityRequest {
    private Integer sportCourtId;
    private Integer weekday;

    private LocalTime startTime;

    private LocalTime endTime;
}
