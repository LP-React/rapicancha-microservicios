package com.microservice.court.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalTime;

@Data
public class VenueResponse {
    private Integer idVenue;
    private String name;
    private String address;
    private String description;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Integer maxCapacity;
    private String bannerImageUrl;
    private Integer ownerAccountId;
    private String ownerName;
    private Boolean hasParking;
    private Integer parkingCapacity;
    private Boolean hasLockerRoom;
    private Boolean hasRestroom;
    private Boolean hasStore;
    private Boolean hasShower;
    private Boolean providesBalls;
    private Boolean providesBibs;
    private Boolean providesEquipment;
    private Boolean is_active;
}
