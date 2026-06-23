package com.microservice.booking.client;

import org.springframework.stereotype.Component;

@Component
public class CourtClientFallback implements CourtClient {

    @Override
    public CourtResponse getCourt(Integer id) {
        CourtResponse fallback = new CourtResponse();
        fallback.setIdSportCourt(id != null ? id : -1);
        fallback.setName("Cancha temporalmente no disponible (Modo Resiliencia)");

        return fallback;
    }
}
