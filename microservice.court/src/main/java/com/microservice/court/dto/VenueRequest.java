package com.microservice.court.dto;

import lombok.Data;

import java.math.BigDecimal;

import java.time.LocalTime;

@Data
public class VenueRequest {
    private Integer ownerAccountId;
    private String name;
    private String address;
    private String description;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Integer maxCapacity;
    private Integer parkingCapacity;
    private String bannerImageUrl;
    private Boolean providesEquipment;
    private Boolean hasParking;
    private Boolean hasLockerRoom;
    private Boolean hasRestroom;
    private Boolean hasStore;
    private Boolean hasShower;
    private Boolean providesBalls;
    private Boolean providesBibs;
}
