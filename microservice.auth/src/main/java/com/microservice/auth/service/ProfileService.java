package com.microservice.auth.service;

import com.microservice.auth.dto.CustomerProfileResponse;
import com.microservice.auth.dto.CustomerProfileUpdateRequest;
import com.microservice.auth.dto.OwnerProfileResponse;
import com.microservice.auth.dto.OwnerProfileUpdateRequest;

import com.microservice.auth.entity.CustomerProfile;
import com.microservice.auth.entity.OwnerProfile;

import com.microservice.auth.repository.CustomerProfileRepository;
import com.microservice.auth.repository.OwnerProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private OwnerProfileRepository ownerRepository;

    @Autowired
    private CustomerProfileRepository customerRepository;


    public OwnerProfileResponse updateOwner(

            Integer accountId,

            OwnerProfileUpdateRequest request){

        OwnerProfile owner=
                ownerRepository
                        .findById(
                                accountId
                        )
                        .orElseThrow(
                                ()->new RuntimeException(
                                        "Perfil dueño no encontrado"
                                )
                        );

        owner.setFirstName(
                request.getFirstName()
        );

        owner.setLastName(
                request.getLastName()
        );

        owner.setNationalId(
                request.getNationalId()
        );

        owner.setPhone(
                request.getPhone()
        );

        return convertToOwnerResponse(
                ownerRepository.save(
                        owner
                )
        );

    }


    public CustomerProfileResponse updateCustomer(

            Integer accountId,

            CustomerProfileUpdateRequest request){

        CustomerProfile customer=
                customerRepository
                        .findById(
                                accountId
                        )
                        .orElseThrow(
                                ()->new RuntimeException(
                                        "Perfil cliente no encontrado"
                                )
                        );

        customer.setFirstName(
                request.getFirstName()
        );

        customer.setLastName(
                request.getLastName()
        );

        customer.setPhone(
                request.getPhone()
        );

        return convertToCustomerResponse(
                customerRepository.save(
                        customer
                )
        );

    }


    private OwnerProfileResponse
    convertToOwnerResponse(
            OwnerProfile owner){

        OwnerProfileResponse resp=
                new OwnerProfileResponse();

        resp.setAccountId(
                owner.getAccountId()
        );

        resp.setEmail(
                owner.getAccount()
                        .getEmail()
        );

        resp.setFirstName(
                owner.getFirstName()
        );

        resp.setLastName(
                owner.getLastName()
        );

        resp.setNationalId(
                owner.getNationalId()
        );

        resp.setPhone(
                owner.getPhone()
        );

        return resp;

    }


    private CustomerProfileResponse
    convertToCustomerResponse(
            CustomerProfile customer){

        CustomerProfileResponse resp=
                new CustomerProfileResponse();

        resp.setAccountId(
                customer.getAccountId()
        );

        resp.setEmail(
                customer.getAccount()
                        .getEmail()
        );

        resp.setFirstName(
                customer.getFirstName()
        );

        resp.setLastName(
                customer.getLastName()
        );

        resp.setPhone(
                customer.getPhone()
        );

        return resp;

    }


    public CustomerProfileResponse getCustomer(Integer id){

        CustomerProfile customer=
                customerRepository
                        .findById(id)
                        .orElseThrow(
                                ()->new RuntimeException(
                                        "Cliente no encontrado"
                                )
                        );

        return convertToCustomerResponse(
                customer
        );

    }


    public OwnerProfileResponse getOwner(Integer id){

        OwnerProfile owner=
                ownerRepository
                        .findById(id)
                        .orElseThrow(
                                ()->new RuntimeException(
                                        "Owner no encontrado"
                                )
                        );

        return convertToOwnerResponse(
                owner
        );

    }

}