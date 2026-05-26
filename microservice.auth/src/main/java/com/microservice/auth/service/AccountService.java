package com.microservice.auth.service;

import com.microservice.auth.entity.Account;
import com.microservice.auth.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void deleteAccount(Integer id){

        Account account=
                accountRepository
                .findById(id)
                .orElseThrow(
                        ()->new RuntimeException(
                                "Cuenta no encontrada"
                        )
                );

        account.setStatus(
                "DELETED"
        );

        account.setUpdatedAt(
                LocalDateTime.now()
        );

        accountRepository.save(
                account
        );
    }

}