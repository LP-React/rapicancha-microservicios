package com.microservice.court.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SportCourtDetailResponse extends SportCourtResponse {
    private List<AvailabilityResponse> availability;
}
