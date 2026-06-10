package com.microservice.court.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="sport_court_image")
@Getter
@Setter
public class SportCourtImage {

    @Id
    @GeneratedValue(strategy=
            GenerationType.IDENTITY)
    private Integer idSportCourtImage;

    private Integer sportCourtId;

    @Column(
            name="image_url",
            length=500,
            nullable=false
    )
    private String imageUrl;

}