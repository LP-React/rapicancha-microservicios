package com.microservice.auth.controller;

import com.microservice.auth.dto.CustomerProfileResponse;
import com.microservice.auth.dto.CustomerProfileUpdateRequest;

import com.microservice.auth.dto.OwnerProfileResponse;
import com.microservice.auth.dto.OwnerProfileUpdateRequest;

import com.microservice.auth.service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService service;


    @PutMapping("/customer/{id}")
    public CustomerProfileResponse
    updateCustomer(

            @PathVariable
            Integer id,

            @RequestBody
            CustomerProfileUpdateRequest request){

        return service.updateCustomer(
                id,
                request
        );

    }


    @PutMapping("/owner/{id}")
    public OwnerProfileResponse
    updateOwner(

            @PathVariable
            Integer id,

            @RequestBody
            OwnerProfileUpdateRequest request){

        return service.updateOwner(
                id,
                request
        );

    }

    @GetMapping("/customer/{id}")
    public CustomerProfileResponse
    getCustomer(

            @PathVariable
            Integer id){

        return service.getCustomer(
                id
        );

    }


    @GetMapping("/owner/{id}")
    public OwnerProfileResponse
    getOwner(

            @PathVariable
            Integer id){

        return service.getOwner(
                id
        );

    }

}