package com.microservice.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;



@FeignClient(
        name="mcsv-auth",
        fallback = AuthClientFallback.class
)
public interface AuthClient {

@GetMapping(
"/api/customer/{id}"
)
CustomerResponse getCustomer(
@PathVariable("id") Integer id
);

}