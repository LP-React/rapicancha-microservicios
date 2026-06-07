package com.microservice.auth.service;

import com.microservice.auth.entity.Account;
import com.microservice.auth.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void deleteAccount(Integer accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada con id: " + accountId));

        account.setStatus("DELETED");
        account.setUpdatedAt(LocalDateTime.now());
        accountRepository.save(account);
    }
}