package com.microservice.court.repository;

import com.microservice.court.entity.SportCourt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SportCourtRepository
extends JpaRepository<
        SportCourt,
        Integer>{

    List<SportCourt>
    findByVenueIdAndIsActiveTrue(
            Integer venueId
    );

    List<SportCourt>
    findByIsActiveTrue();

    long countByVenueIdAndIsActiveTrue(Integer venueId);
}