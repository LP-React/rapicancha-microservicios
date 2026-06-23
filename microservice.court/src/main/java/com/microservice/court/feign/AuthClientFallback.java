package com.microservice.court.feign;

import com.microservice.court.dto.OwnerResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthClientFallback implements AuthClient {

    @Override
    public OwnerResponse getOwner(Integer id) {
        OwnerResponse fallback = new OwnerResponse();
        fallback.setAccountId(id != null ? id : -1);
        fallback.setFirstName("Propietario");
        fallback.setLastName("Temporal (Modo Resiliencia)");
        return fallback;
    }
}
