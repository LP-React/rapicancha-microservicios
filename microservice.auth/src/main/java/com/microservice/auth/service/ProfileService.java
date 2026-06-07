package com.microservice.auth.service;

import com.microservice.auth.dto.CustomerProfileResponse;
import com.microservice.auth.dto.CustomerProfileUpdateRequest;
import com.microservice.auth.dto.OwnerProfileResponse;
import com.microservice.auth.dto.OwnerProfileUpdateRequest;
import com.microservice.auth.entity.Account;
import com.microservice.auth.entity.CustomerProfile;
import com.microservice.auth.entity.OwnerProfile;
import com.microservice.auth.entity.Role;
import com.microservice.auth.repository.CustomerProfileRepository;
import com.microservice.auth.repository.OwnerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final OwnerProfileRepository ownerRepository;
    private final CustomerProfileRepository customerRepository;

    public void createCustomerProfile(Account account, String firstName, String lastName, String phone) {
        CustomerProfile customer = new CustomerProfile();
        customer.setAccount(account);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhone(phone);
        customerRepository.save(customer);
    }

    public void createOwnerProfile(Account account, String firstName, String lastName, String phone, String nationalId) {
        OwnerProfile owner = new OwnerProfile();
        owner.setAccount(account);
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setPhone(phone);
        owner.setNationalId(nationalId);
        ownerRepository.save(owner);
    }

    public CustomerProfileResponse getCustomer(Integer accountId) {
        CustomerProfile customer = findCustomerOrThrow(accountId);
        return toCustomerResponse(customer);
    }

    public OwnerProfileResponse getOwner(Integer accountId) {
        OwnerProfile owner = findOwnerOrThrow(accountId);
        return toOwnerResponse(owner);
    }

    public record ProfileInfo(String firstName, String lastName, Integer profileId) {}

    public ProfileInfo getProfileInfo(Integer accountId, Role role) {
        if (role == Role.OWNER) {
            return ownerRepository.findById(accountId)
                    .map(o -> new ProfileInfo(o.getFirstName(), o.getLastName(), o.getAccountId()))
                    .orElse(new ProfileInfo(null, null, null));
        } else if (role == Role.CUSTOMER) {
            return customerRepository.findById(accountId)
                    .map(c -> new ProfileInfo(c.getFirstName(), c.getLastName(), c.getAccountId()))
                    .orElse(new ProfileInfo(null, null, null));
        }
        return new ProfileInfo(null, null, null);
    }

    public CustomerProfileResponse updateCustomer(Integer accountId, CustomerProfileUpdateRequest request) {
        CustomerProfile customer = findCustomerOrThrow(accountId);
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setPhone(request.phone());
        return toCustomerResponse(customerRepository.save(customer));
    }

    public OwnerProfileResponse updateOwner(Integer accountId, OwnerProfileUpdateRequest request) {
        OwnerProfile owner = findOwnerOrThrow(accountId);
        owner.setFirstName(request.firstName());
        owner.setLastName(request.lastName());
        owner.setNationalId(request.nationalId());
        owner.setPhone(request.phone());
        return toOwnerResponse(ownerRepository.save(owner));
    }

    private CustomerProfile findCustomerOrThrow(Integer accountId) {
        return customerRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id: " + accountId));
    }

    private OwnerProfile findOwnerOrThrow(Integer accountId) {
        return ownerRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Dueño no encontrado con id: " + accountId));
    }

    private CustomerProfileResponse toCustomerResponse(CustomerProfile customer) {
        return new CustomerProfileResponse(
                customer.getAccountId(),
                customer.getAccount().getEmail(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhone()
        );
    }

    private OwnerProfileResponse toOwnerResponse(OwnerProfile owner) {
        return new OwnerProfileResponse(
                owner.getAccountId(),
                owner.getAccount().getEmail(),
                owner.getFirstName(),
                owner.getLastName(),
                owner.getNationalId(),
                owner.getPhone()
        );
    }
}