package com.microservice.court.controller;

import com.microservice.court.dto.AvailabilityRequest;
import com.microservice.court.dto.AvailabilityResponse;
import com.microservice.court.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService service;

    @GetMapping
    public List<AvailabilityResponse> getAll() {
        return service.getAllActive();
    }

    @GetMapping("/{id}")
    public AvailabilityResponse getById(@PathVariable("id") Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public AvailabilityResponse create(@RequestBody AvailabilityRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public AvailabilityResponse update(@PathVariable("id") Integer id, @RequestBody AvailabilityRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}