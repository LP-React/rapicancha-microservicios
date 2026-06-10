package com.microservice.court.controller;

import com.microservice.court.dto.VenueRequest;
import com.microservice.court.dto.VenueResponse;
import com.microservice.court.dto.VenueWithCourtsResponse;

import com.microservice.court.service.VenueService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
public class VenueController {

    @Autowired
    private VenueService service;


    @GetMapping
    public List<VenueResponse>
    getAll(){

        return service.getAllVenues();

    }


    @GetMapping("/{id}")
    public VenueResponse getById(

            @PathVariable
            Integer id){

        return service.getVenueById(
                id
        );

    }


    @GetMapping("/owner/{ownerId}")
    public List<VenueWithCourtsResponse>
    getByOwner(

            @PathVariable
            Integer ownerId){

        return service.getVenuesAndCourtsByOwner(
                ownerId
        );

    }


    @PostMapping
    public VenueResponse create(

            @RequestBody
            VenueRequest request){

        return service.createVenue(
                request
        );

    }


    @PutMapping("/{id}")
    public VenueResponse update(

            @PathVariable
            Integer id,

            @RequestBody
            VenueRequest request){

        return service.updateVenue(
                id,
                request
        );

    }


    @DeleteMapping("/{id}")
    public void delete(

            @PathVariable
            Integer id){

        service.deleteVenue(
                id
        );

    }

}