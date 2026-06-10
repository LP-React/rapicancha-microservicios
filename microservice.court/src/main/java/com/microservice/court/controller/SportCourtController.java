package com.microservice.court.controller;

import com.microservice.court.dto.SportCourtDetailResponse;
import com.microservice.court.dto.SportCourtRequest;
import com.microservice.court.dto.SportCourtResponse;

import com.microservice.court.service.SportCourtService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courts")
public class SportCourtController {

    @Autowired
    private SportCourtService service;


    @GetMapping
    public List<SportCourtResponse>
    getAll(

            @RequestParam(
                    required=false
            )
            Integer venueId){

        return service.getAllSportCourts(
                venueId
        );

    }


    @GetMapping("/{id}")
    public SportCourtDetailResponse
    getDetail(

            @PathVariable
            Integer id){

        return service.getCourtDetail(
                id
        );

    }


    @PostMapping
    public SportCourtResponse create(

            @RequestBody
            SportCourtRequest request){

        return service.createSportCourt(
                request
        );

    }


    @PutMapping("/{id}")
    public SportCourtResponse update(

            @PathVariable
            Integer id,

            @RequestBody
            SportCourtRequest request){

        return service.updateSportCourt(
                id,
                request
        );

    }


    @DeleteMapping("/{id}")
    public void delete(

            @PathVariable
            Integer id){

        service.deleteSportCourt(
                id
        );

    }

}