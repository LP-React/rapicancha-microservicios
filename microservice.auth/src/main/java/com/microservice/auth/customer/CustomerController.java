package com.microservice.auth.customer;

import com.microservice.auth.customer.dto.CustomerProfileResponse;
import com.microservice.auth.customer.dto.CustomerProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public CustomerProfileResponse getCustomer(@PathVariable("id") Integer id) {
        return customerService.getCustomer(id);
    }

    @PutMapping("/{id}")
    public CustomerProfileResponse updateCustomer(@PathVariable("id") Integer id, @RequestBody CustomerProfileUpdateRequest request) {
        return customerService.updateCustomer(id, request);
    }
}
