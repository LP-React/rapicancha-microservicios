package com.microservice.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        name="mcsv-court",
        fallback = CourtClientFallback.class
)
public interface CourtClient {

@GetMapping(
"/api/sport-courts/{id}"
)
CourtResponse getCourt(
@PathVariable("id") Integer id
);

}