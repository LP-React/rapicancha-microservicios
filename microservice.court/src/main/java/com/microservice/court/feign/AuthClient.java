package com.microservice.court.feign;

import com.microservice.court.dto.OwnerResponse;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.*;

@FeignClient(
        name="mcsv-auth",
        fallback = AuthClientFallback.class
)
public interface AuthClient {

    @GetMapping(
            "/api/owner/{id}"
    )
    OwnerResponse getOwner(
            @PathVariable("id")
            Integer id
    );

}