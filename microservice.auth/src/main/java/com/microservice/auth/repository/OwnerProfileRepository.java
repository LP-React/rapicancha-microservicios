package com.microservice.auth.repository;

import com.microservice.auth.entity.OwnerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerProfileRepository
extends JpaRepository<
        OwnerProfile,
        Integer>{

}