package com.microservice.court.controller;

import com.microservice.court.dto.VenueRequest;
import com.microservice.court.dto.VenueResponse;
import com.microservice.court.dto.VenueWithCourtsResponse;
import com.microservice.court.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService service;

    @GetMapping
    public List<VenueResponse> getAll(@RequestParam(required = false) Integer ownerId) {
        return service.getAllVenues(ownerId);
    }

    @GetMapping("/{id}")
    public VenueResponse getById(@PathVariable("id") Integer id) {
        return service.getVenueById(id);
    }

    @GetMapping("/owner/{ownerId}")
    public List<VenueWithCourtsResponse> getByOwner(@PathVariable Integer ownerId) {
        return service.getVenuesAndCourtsByOwner(ownerId);
    }

    @PostMapping
    public VenueResponse create(@RequestBody VenueRequest request) {
        return service.createVenue(request);
    }

    @PutMapping("/{id}")
    public VenueResponse update(@PathVariable("id") Integer id, @RequestBody VenueRequest request) {
        return service.updateVenue(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.deleteVenue(id);
    }
}