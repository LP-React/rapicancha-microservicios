package com.microservice.court.controller;

import com.microservice.court.dto.VenueWithCourtsResponse;
import com.microservice.court.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VenueAndCourtController {

    private final VenueService venueService;

    @GetMapping("/api/venues-and-sport-court")
    public List<VenueWithCourtsResponse> getVenuesAndCourts(
            @RequestParam("idOwner") Integer idOwner) {

        return venueService.getVenuesAndCourtsByOwner(idOwner);
    }
}