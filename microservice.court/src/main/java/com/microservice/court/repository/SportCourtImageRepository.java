package com.microservice.court.repository;

import com.microservice.court.entity.SportCourtImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SportCourtImageRepository
extends JpaRepository<
        SportCourtImage,
        Integer>{

    List<SportCourtImage>
    findBySportCourtId(
            Integer sportCourtId
    );

}