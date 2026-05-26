package com.microservice.court.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.*;
import java.util.List;

@Entity
@Table(name="venue")
@Getter
@Setter
public class Venue {

    @Id
    @GeneratedValue(strategy=
            GenerationType.IDENTITY)
    private Integer idVenue;

    private Integer ownerAccountId;

    @Column(nullable=false,length=100)
    private String name;

    @Column(nullable=false,length=200)
    private String address;

    @Column(
            precision=10,
            scale=8,
            nullable=false
    )
    private BigDecimal latitude;

    @Column(
            precision=11,
            scale=8,
            nullable=false
    )
    private BigDecimal longitude;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(
            name="banner_image_url",
            length=500
    )
    private String bannerImageUrl;

    private Boolean providesEquipment=false;

    private LocalTime openTime;

    private LocalTime closeTime;

    private Integer maxCapacity;

    private Integer parkingCapacity=0;

    private Boolean hasParking=false;

    private Boolean hasLockerRoom=false;

    private Boolean hasRestroom=true;

    private Boolean hasStore=false;

    private Boolean hasShower=false;

    private Boolean providesBalls=false;

    private Boolean providesBibs=false;

    private Boolean is_active=true;

    private LocalDateTime createdAt=
            LocalDateTime.now();

    private LocalDateTime updatedAt=
            LocalDateTime.now();

}