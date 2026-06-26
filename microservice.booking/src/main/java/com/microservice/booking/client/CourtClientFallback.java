package com.microservice.booking.client;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;

@Component
public class CourtClientFallback implements CourtClient {

    @Override
    public CourtResponse getCourt(Integer id) {
        CourtResponse fallback = new CourtResponse();

        fallback.setIdSportCourt(id != null ? id : -1);
        fallback.setVenueId(-1);

        fallback.setName("Cancha temporalmente no disponible (Modo Resiliencia)");
        fallback.setVenueName("Sede no disponible");
        fallback.setDescription("La información detallada de esta cancha no está disponible en este momento debido a un problema de conexión.");
        fallback.setSportType("Desconocido");
        fallback.setSurfaceType("Desconocida");

        fallback.setCapacity(0);
        fallback.setHasLighting(false);
        fallback.setHasRoof(false);
        fallback.setRules("Información de reglas no disponible.");

        fallback.setPlayMinutes(0);
        fallback.setSlotMinutes(0);
        fallback.setRate(BigDecimal.ZERO);

        fallback.setIsActive(false);
        fallback.setAvailability(null);

        fallback.setImages(Collections.emptyList());

        return fallback;
    }
}