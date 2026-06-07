package com.microservice.auth.service;

import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.LoginResponse;
import com.microservice.auth.dto.RegisterRequest;
import com.microservice.auth.dto.RegisterResponse;
import com.microservice.auth.entity.Account;
import com.microservice.auth.entity.Role;
import com.microservice.auth.repository.AccountRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepository accountRepository;
    private final ProfileService profileService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        verifyEmailNotTaken(request.email());

        Account account = buildAccount(request);
        Account saved = accountRepository.save(account);

        createProfile(saved, request);

        return toRegisterResponse(saved);
    }

    public LoginResponse login(LoginRequest request) {
        Account account = findAccountByEmailOrThrow(request.email());
        verifyPassword(request.password(), account.getPasswordHash());

        String token = generateToken(account);

        return buildLoginResponse(account, token);
    }

    private void verifyEmailNotTaken(String email) {
        if (accountRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado: " + email);
        }
    }

    private Account buildAccount(RegisterRequest request) {
        Account account = new Account();
        account.setEmail(request.email());
        account.setPasswordHash(passwordEncoder.encode(request.password()));
        account.setRole(request.role());
        return account;
    }

    private void createProfile(Account account, RegisterRequest request) {
        if (account.getRole() == Role.CUSTOMER) {
            profileService.createCustomerProfile(
                    account, request.firstName(), request.lastName(), request.phone()
            );
        } else if (account.getRole() == Role.OWNER) {
            profileService.createOwnerProfile(
                    account, request.firstName(), request.lastName(), request.phone(), request.nationalId()
            );
        }
    }

    private Account findAccountByEmailOrThrow(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Credenciales incorrectas"));
    }

    private void verifyPassword(String rawPassword, String storedHash) {
        if (!passwordEncoder.matches(rawPassword, storedHash)) {
            throw new IllegalArgumentException("Credenciales incorrectas");
        }
    }

    private String generateToken(Account account) {
        return jwtService.generateToken(account.getEmail(), buildClaims(account));
    }

    private Map<String, Object> buildClaims(Account account) {
        return Map.of(
                "role", account.getRole().name(),
                "accountId", account.getIdAccount()
        );
    }

    private LoginResponse buildLoginResponse(Account account, String token) {
        ProfileService.ProfileInfo info = profileService.getProfileInfo(account.getIdAccount(), account.getRole());
        return new LoginResponse(
                account.getIdAccount(),
                account.getEmail(),
                account.getRole().name(),
                info.firstName(),
                info.lastName(),
                info.profileId(),
                token
        );
    }

    private RegisterResponse toRegisterResponse(Account account) {
        return new RegisterResponse(
                account.getIdAccount(),
                account.getEmail(),
                account.getRole(),
                account.getStatus(),
                account.getCreatedAt()
        );
    }
}