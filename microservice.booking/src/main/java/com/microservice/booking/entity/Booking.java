package com.microservice.booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.*;

@Entity
@Table(name="booking")
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy=
            GenerationType.IDENTITY)

    private Integer idBooking;

    private Integer sportCourtId;

    private Integer customerAccountId;

    @Column(nullable=false)
    private LocalDate date;

    @Column(name="start_time")
    private LocalTime startTime;

    @Column(name="end_time")
    private LocalTime endTime;

    @Column(
            nullable=false,
            precision=8,
            scale=2
    )
    private BigDecimal price;

    @Column(
            name="qr_code",
            unique=true
    )
    private String qrCode;

    private LocalDateTime checkedInAt;

    @Enumerated(
            EnumType.STRING
    )
    private BookingStatus status=
            BookingStatus.PENDING;

    @Column(
            name="created_at",
            updatable=false
    )
    private LocalDateTime createdAt=
            LocalDateTime.now();

}