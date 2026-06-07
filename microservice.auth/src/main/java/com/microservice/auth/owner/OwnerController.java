package com.microservice.auth.owner;

import com.microservice.auth.owner.dto.OwnerProfileResponse;
import com.microservice.auth.owner.dto.OwnerProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/{id}")
    public OwnerProfileResponse getOwner(@PathVariable("id") Integer id) {
        return ownerService.getOwner(id);
    }

    @PutMapping("/{id}")
    public OwnerProfileResponse updateOwner(@PathVariable("id") Integer id, @RequestBody OwnerProfileUpdateRequest request) {
        return ownerService.updateOwner(id, request);
    }
}
