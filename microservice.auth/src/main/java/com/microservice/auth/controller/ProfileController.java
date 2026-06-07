package com.microservice.auth.controller;

import com.microservice.auth.dto.CustomerProfileResponse;
import com.microservice.auth.dto.CustomerProfileUpdateRequest;
import com.microservice.auth.dto.OwnerProfileResponse;
import com.microservice.auth.dto.OwnerProfileUpdateRequest;
import com.microservice.auth.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/customer/{id}")
    public CustomerProfileResponse getCustomer(@PathVariable("id") Integer id) {
        return profileService.getCustomer(id);
    }

    @GetMapping("/owner/{id}")
    public OwnerProfileResponse getOwner(@PathVariable("id") Integer id) {
        return profileService.getOwner(id);
    }

    @PutMapping("/customer/{id}")
    public CustomerProfileResponse updateCustomer(@PathVariable("id") Integer id, @RequestBody CustomerProfileUpdateRequest request) {
        return profileService.updateCustomer(id, request);
    }

    @PutMapping("/owner/{id}")
    public OwnerProfileResponse updateOwner(@PathVariable("id") Integer id, @RequestBody OwnerProfileUpdateRequest request) {
        return profileService.updateOwner(id, request);
    }
}