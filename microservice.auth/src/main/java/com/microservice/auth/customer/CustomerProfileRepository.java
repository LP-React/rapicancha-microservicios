package com.microservice.auth.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Integer>{

    Optional<CustomerProfile> findByAccountId(Integer accountId);
}