package com.microservice.booking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="pending_booking")
@Data
public class PendingBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer sportCourtId;

    private Integer customerAccountId;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private BigDecimal price;

    private Boolean processed = false;


}