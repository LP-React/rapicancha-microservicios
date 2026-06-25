package com.microservice.court.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="sport_court")
@Getter
@Setter
public class SportCourt {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idSportCourt;

    private Integer venueId;

    @Column(nullable=false, length=50)
    private String name;

    @Column(columnDefinition="TEXT")
    private String description;

    @Transient
    private List<SportCourtImage> images= new ArrayList<>();

    @Column(name="sport_type", nullable=false, length=30)
    private String sportType;

    @Column(name="surface_type", length=30)
    private String surfaceType;

    private Integer capacity=0;
    private Boolean hasRoof=false;
    private Boolean hasLighting=true;

    @Column(columnDefinition="TEXT")
    private String rules;

    @Column(nullable=false, precision=8, scale=2)
    private BigDecimal rate;

    private Integer slotMinutes=60;
    private Integer playMinutes=45;
    private Boolean isActive=true;

}