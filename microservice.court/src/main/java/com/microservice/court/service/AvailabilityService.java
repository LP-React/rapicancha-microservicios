package com.microservice.court.service;

import com.microservice.court.dto.AvailabilityRequest;
import com.microservice.court.dto.AvailabilityResponse;
import com.microservice.court.entity.SportCourtAvailability;
import com.microservice.court.repository.AvailabilityRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    public List<AvailabilityResponse> getAllActive(){
        return availabilityRepository.findByIsActiveTrue()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<AvailabilityResponse> getByCourt(Integer courtId){
        return availabilityRepository.findBySportCourtIdAndIsActiveTrue(courtId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public AvailabilityResponse getById(Integer id){
        SportCourtAvailability availability= availabilityRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Disponibilidad no encontrada"));

        return convertToResponse(availability);
    }

    public AvailabilityResponse create(AvailabilityRequest request){
        SportCourtAvailability availability= new SportCourtAvailability();
        availability.setSportCourtId(request.getSportCourtId());

        mapRequestToEntity(request, availability);

        return convertToResponse(availabilityRepository.save(availability));
    }

    public AvailabilityResponse update(Integer id, AvailabilityRequest request){

        SportCourtAvailability availability= availabilityRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Disponibilidad no encontrada"));

        mapRequestToEntity(request, availability);

        return convertToResponse(availabilityRepository.save(availability));
    }

    public void delete(Integer id){
        SportCourtAvailability availability= availabilityRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Disponibilidad no encontrada"));

        availability.setIsActive(false);

        availabilityRepository.save(availability);
    }

    private void mapRequestToEntity(AvailabilityRequest request, SportCourtAvailability entity){
        entity.setWeekday(request.getWeekday());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
    }

    private AvailabilityResponse convertToResponse(SportCourtAvailability entity){
        AvailabilityResponse response= new AvailabilityResponse();

        response.setIdAvailability(entity.getIdAvailability());
        response.setSportCourtId(entity.getSportCourtId());
        response.setWeekday(entity.getWeekday());
        response.setStartTime(entity.getStartTime());
        response.setEndTime(entity.getEndTime());
        response.setIsActive(entity.getIsActive());

        return response;
    }
}