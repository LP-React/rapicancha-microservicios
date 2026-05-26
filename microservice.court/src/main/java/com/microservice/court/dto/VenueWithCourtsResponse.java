package com.microservice.court.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class VenueWithCourtsResponse {
    private Integer idVenue;
    private String name;

    @JsonProperty("sport_courts")
    private List<SportCourtSimpleResponse> sportCourts;
}
