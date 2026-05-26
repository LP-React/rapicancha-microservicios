package com.microservice.auth.controller;

import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.LoginResponse;
import com.microservice.auth.dto.RegisterRequest;

import com.microservice.auth.entity.Account;

import com.microservice.auth.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService service;


    @PostMapping("/register")
    public Account register(

            @RequestBody
            RegisterRequest request){

        return service.register(
                request
        );

    }


    @PostMapping("/login")
    public LoginResponse login(

            @RequestBody
            LoginRequest request){

        return service.login(
                request
        );

    }

}