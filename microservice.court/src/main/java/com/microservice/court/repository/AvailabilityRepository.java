package com.microservice.court.repository;

import com.microservice.court.entity.SportCourtAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailabilityRepository
extends JpaRepository<
        SportCourtAvailability,
        Integer>{

    List<SportCourtAvailability>
    findBySportCourtIdAndIsActiveTrue(
            Integer courtId
    );

    List<SportCourtAvailability>
    findByIsActiveTrue();

}