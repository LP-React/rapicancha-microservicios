package com.microservice.court.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name="sport_court_availability")
@Getter
@Setter
public class SportCourtAvailability {

    @Id
    @GeneratedValue(strategy=
            GenerationType.IDENTITY)
    private Integer idAvailability;

    private Integer sportCourtId;

    @Column(nullable=false)
    private Integer weekday;

    @Column(
            name="start_time",
            nullable=false
    )
    private LocalTime startTime;

    @Column(
            name="end_time",
            nullable=false
    )
    private LocalTime endTime;

    @Column(name="is_active")
    private Boolean isActive=true;

}