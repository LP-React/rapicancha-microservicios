package com.microservice.court.feign;

import com.microservice.court.dto.OwnerResponse;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.*;

@FeignClient(
        name="mcsv-auth"
)
public interface AuthClient {

    @GetMapping(
            "/api/profile/owner/{id}"
    )
    OwnerResponse getOwner(
            @PathVariable
            Integer id
    );

}