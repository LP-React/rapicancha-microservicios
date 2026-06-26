package com.microservice.booking.client;

import org.springframework.stereotype.Component;

@Component
public class AuthClientFallback implements AuthClient {

    @Override
    public CustomerResponse getCustomer(Integer id) {
        CustomerResponse fallback = new CustomerResponse();

        fallback.setAccountId(id != null ? id : -1);
        fallback.setFirstName("Cliente");
        fallback.setLastName("Temporal (Modo Resiliencia)");
        fallback.setPhone("0000000000");
        fallback.setEmail("no-disponible@cibertec.edu.pe");

        return fallback;
    }
}