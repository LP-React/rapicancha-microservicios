package com.microservice.auth.account;

import com.microservice.auth.account.Account;
import com.microservice.auth.account.AccountRepository;
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