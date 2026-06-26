package com.microservice.booking.client;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CourtResponse {

    // Identificadores
    private Integer idSportCourt;
    private Integer venueId;

    // Informacion General
    private String name;
    private String venueName;
    private String description;
    private String sportType;
    private String surfaceType;

    // Configuracion y Reglas
    private Integer capacity;
    private Boolean hasLighting;
    private Boolean hasRoof;
    private String rules;

    // Tiempos y Precios
    private Integer playMinutes;
    private Integer slotMinutes;
    private BigDecimal rate;

    // Estado
    private Boolean isActive;
    private Object availability;

    private List<CourtImageResponse> images;

    @Data
    public static class CourtImageResponse {
        private Integer idSportCourtImage;
        private String imageUrl;
    }
}