package com.microservice.auth.repository;

import com.microservice.auth.entity.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProfileRepository
extends JpaRepository<
        CustomerProfile,
        Integer>{

}