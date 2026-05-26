package com.microservice.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(
name="mcsv-court"
)
public interface CourtClient {

@GetMapping(
"/api/courts/{id}"
)
CourtResponse getCourt(
@PathVariable Integer id
);

}