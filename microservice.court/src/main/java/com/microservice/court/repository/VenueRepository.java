package com.microservice.court.repository;

import com.microservice.court.entity.Venue;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface VenueRepository
extends JpaRepository<
        Venue,
        Integer
>,
JpaSpecificationExecutor<Venue>{

    List<Venue>
    findByOwnerAccountId(
            Integer ownerId
    );

    
}