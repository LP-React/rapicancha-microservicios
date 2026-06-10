package com.microservice.court.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SportCourtResponse {
    private Integer idSportCourt;
    private Integer venueId;
    private String venueName;
    private String name;
    private String description;
    private String sportType;
    private String surfaceType;
    private Integer capacity;
    private String rules;
    private BigDecimal rate;
    private Integer slotMinutes;
    private Integer playMinutes;
    private Boolean hasRoof;
    private Boolean hasLighting;
    private Boolean isActive;
    private List<SportCourtImageResponse> images;
}
