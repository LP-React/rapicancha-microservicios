package com.microservice.auth.controller;

import com.microservice.auth.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @DeleteMapping("/{id}")
    public void deleteAccount(

            @PathVariable
            Integer id){

        service.deleteAccount(
                id
        );

    }

}