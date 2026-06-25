package com.microservice.booking.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingResponse {

    private Integer idBooking;
    private Integer idSportCourt;
    private String courtName;
    private String customerName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal price;
    private String status;
    private String qrCode;

}