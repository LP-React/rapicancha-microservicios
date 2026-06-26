package com.microservice.court.feign;

import com.microservice.court.dto.OwnerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthClientFallback implements AuthClient {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthClientFallback.class);

    @Override
    public OwnerResponse getOwner(Integer id) {
        LOGGER.warn("Ejecutando Fallback para getOwner con id: {}", id);

        OwnerResponse fallback = new OwnerResponse();
        fallback.setAccountId(id != null ? id : -1);
        fallback.setFirstName("Propietario");
        fallback.setLastName("Temporal (Modo Resiliencia)");

        fallback.setEmail("no-disponible@cibertec.edu.pe");
        fallback.setNationalId("00000000");
        fallback.setPhone("0000000000");

        return fallback;
    }
}
