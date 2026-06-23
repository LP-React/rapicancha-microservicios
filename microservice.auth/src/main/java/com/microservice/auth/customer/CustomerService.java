package com.microservice.auth.customer;

import com.microservice.auth.account.Account;
import com.microservice.auth.customer.dto.CustomerProfileResponse;
import com.microservice.auth.customer.dto.CustomerProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerProfileRepository customerRepository;

    public void createCustomerProfile(Account account, String firstName, String lastName, String phone) {
        CustomerProfile customer = new CustomerProfile();
        customer.setAccount(account);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhone(phone);
        customerRepository.save(customer);
    }

    public CustomerProfileResponse getCustomer(Integer accountId) {
        CustomerProfile customer = findCustomerOrThrow(accountId);
        return new CustomerProfileResponse(
                customer.getId(),
                customer.getAccount().getEmail(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhone()
        );
    }

    public CustomerProfileResponse updateCustomer(Integer accountId, CustomerProfileUpdateRequest request) {
        CustomerProfile customer = findCustomerOrThrow(accountId);
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setPhone(request.phone());
        customerRepository.save(customer);
        return getCustomer(accountId);
    }

    public CustomerProfile findCustomerOrThrow(Integer accountId) {
        return customerRepository.findByAccountId(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id: " + accountId));
    }

    public ProfileInfo getProfileInfo(Integer accountId) {
        return customerRepository.findByAccountId(accountId)
                .map(c -> new ProfileInfo(c.getFirstName(), c.getLastName(), c.getId()))
                .orElse(new ProfileInfo(null, null, null));
    }

    public record ProfileInfo(String firstName, String lastName, Integer profileId) {}
}
