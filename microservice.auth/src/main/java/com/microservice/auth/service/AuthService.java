package com.microservice.auth.service;

import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.LoginResponse;
import com.microservice.auth.dto.RegisterRequest;
import com.microservice.auth.entity.Account;
import com.microservice.auth.entity.CustomerProfile;
import com.microservice.auth.entity.OwnerProfile;
import com.microservice.auth.entity.Role;
import com.microservice.auth.repository.AccountRepository;
import com.microservice.auth.repository.CustomerProfileRepository;
import com.microservice.auth.repository.OwnerProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerProfileRepository customerRepository;

    @Autowired
    private OwnerProfileRepository ownerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Transactional
    public Account register(
            RegisterRequest request) {

        if (accountRepository
                .findByEmail(
                        request.getEmail())
                .isPresent()) {

            throw new RuntimeException(
                    "Email existente"
            );

        }

        Account account =
                new Account();

        account.setEmail(
                request.getEmail());

        account.setPasswordHash(
                passwordEncoder.encode(
                        request.getPassword()
                ));

        account.setRole(
                request.getRole()
        );

        Account saved =
                accountRepository.save(
                        account
                );

        if (request.getRole() == Role.CUSTOMER) {

            CustomerProfile customer =
                    new CustomerProfile();

            customer.setAccount(saved);

            customer.setFirstName(
                    request.getFirstName());

            customer.setLastName(
                    request.getLastName());

            customer.setPhone(
                    request.getPhone());

            customerRepository.save(
                    customer
            );

        }

        if (request.getRole() == Role.OWNER) {

            OwnerProfile owner =
                    new OwnerProfile();

            owner.setAccount(
                    saved
            );

            owner.setFirstName(
                    request.getFirstName());

            owner.setLastName(
                    request.getLastName());

            owner.setPhone(
                    request.getPhone());

            owner.setNationalId(
                    request.getNationalId()
            );

            ownerRepository.save(
                    owner
            );

        }

        return saved;

    }

    public LoginResponse login(
            LoginRequest request) {

        Account account =
                accountRepository
                        .findByEmail(
                                request.getEmail()
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Credenciales incorrectas"
                                )
                        );

        if (!passwordEncoder.matches(
                request.getPassword(),
                account.getPasswordHash()
        )) {

            throw new RuntimeException(
                    "Credenciales incorrectas"
            );

        }

        LoginResponse response =
                new LoginResponse();

        response.setAccountId(
                account.getIdAccount()
        );

        response.setEmail(
                account.getEmail()
        );

        response.setRole(
                account.getRole()
                        .name()
        );


        if (account.getRole() == Role.OWNER) {

            ownerRepository
                    .findById(
                            account.getIdAccount()
                    )
                    .ifPresent(owner -> {

                        response.setFirstName(
                                owner.getFirstName()
                        );

                        response.setLastName(
                                owner.getLastName()
                        );

                        response.setProfileId(
                                owner.getAccountId()
                        );

                    });

        } else if (account.getRole() == Role.CUSTOMER) {

            customerRepository
                    .findById(
                            account.getIdAccount()
                    )
                    .ifPresent(customer -> {

                        response.setFirstName(
                                customer.getFirstName()
                        );

                        response.setLastName(
                                customer.getLastName()
                        );

                        response.setProfileId(
                                customer.getAccountId()
                        );

                    });

        }

        return response;

    }
}