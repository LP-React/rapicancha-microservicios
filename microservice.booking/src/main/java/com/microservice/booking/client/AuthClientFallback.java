package com.microservice.booking.client;

import org.springframework.stereotype.Component;

@Component
public class AuthClientFallback implements AuthClient {

    @Override
    public CustomerResponse getCustomer(Integer id) {
        CustomerResponse fallback = new CustomerResponse();
        fallback.setAccountId(id != null ? id : -1);
        fallback.setFirstName("Usuario");
        fallback.setLastName("(Offline)");

        return fallback;
    }
}
