package com.microservice.court.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SportCourtRequest {
    private Integer venueId;
    private String name;
    private String description;
    private String sportType;
    private String surfaceType;
    private Integer capacity;
    private Boolean hasRoof;
    private Boolean hasLighting;
    private String rules;
    private BigDecimal rate;
    private Integer slotMinutes;
    private Integer playMinutes;
    private List<String> imageUrls;
}
