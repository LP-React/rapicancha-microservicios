package com.microservice.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;



@FeignClient(
name="mcsv-auth"
)
public interface AuthClient {

@GetMapping(
"/api/profile/customer/{id}"
)
CustomerResponse getCustomer(
@PathVariable Integer id
);

}