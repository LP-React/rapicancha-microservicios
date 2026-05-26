package com.microservice.court.service;

import com.microservice.court.dto.*;

import com.microservice.court.entity.*;

import com.microservice.court.repository.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SportCourtService {

    @Autowired
    private SportCourtRepository sportCourtRepository;

    @Autowired
    private SportCourtImageRepository imageRepository;

    public List<SportCourtResponse>
    getAllSportCourts(
            Integer venueId){

        List<SportCourt> courts;

        if(venueId!=null){

            courts=
                    sportCourtRepository
                            .findByVenueIdAndIsActiveTrue(
                                    venueId
                            );

        }
        else{

            courts=
                    sportCourtRepository
                            .findByIsActiveTrue();

        }

        return courts.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

    }


    public SportCourtResponse createSportCourt(
            SportCourtRequest request){

        SportCourt court=
                new SportCourt();

        court.setVenueId(
                request.getVenueId()
        );

        mapRequestToEntity(
                request,
                court
        );

        SportCourt saved=
                sportCourtRepository
                        .save(court);

        return convertToResponse(
                saved
        );

    }


    public SportCourtResponse updateSportCourt(
            Integer id,
            SportCourtRequest request){

        SportCourt court=
                sportCourtRepository
                        .findById(id)
                        .orElseThrow(
                                ()->new RuntimeException(
                                        "Cancha no encontrada"
                                )
                        );

        mapRequestToEntity(
                request,
                court
        );

        return convertToResponse(
                sportCourtRepository.save(
                        court
                )
        );

    }

    private void mapRequestToEntity(

            SportCourtRequest request,

            SportCourt court){

        court.setName(
                request.getName()
        );

        court.setDescription(
                request.getDescription()
        );

        court.setRules(
                request.getRules()
        );

        court.setSportType(
                request.getSportType()
        );

        court.setSurfaceType(
                request.getSurfaceType()
        );

        court.setCapacity(
                request.getCapacity()
        );

        court.setRate(
                request.getRate()
        );

    }


    private SportCourtResponse
    convertToResponse(
            SportCourt court){

        SportCourtResponse response=
                new SportCourtResponse();

        response.setIdSportCourt(
                court.getIdSportCourt()
        );

        response.setVenueId(
                court.getVenueId()
        );

        response.setName(
                court.getName()
        );

        response.setDescription(
                court.getDescription()
        );

        response.setSportType(
                court.getSportType()
        );

        response.setSurfaceType(
                court.getSurfaceType()
        );

        response.setCapacity(
                court.getCapacity()
        );

        response.setRate(
                court.getRate()
        );

        return response;

    }
    public SportCourtDetailResponse
    getCourtDetail(
            Integer id){

        SportCourt court=
                sportCourtRepository
                        .findById(id)
                        .orElseThrow(
                                ()->new RuntimeException(
                                        "Cancha no encontrada"
                                )
                        );

        SportCourtResponse baseResponse=
                convertToResponse(
                        court
                );

        SportCourtDetailResponse detail=
                new SportCourtDetailResponse();

        BeanUtils.copyProperties(
                baseResponse,
                detail
        );

        return detail;

    }



    public void deleteSportCourt(
            Integer id){

        SportCourt court=
                sportCourtRepository
                        .findById(id)
                        .orElseThrow(
                                ()->new RuntimeException(
                                        "Cancha no encontrada"
                                )
                        );

        court.setIsActive(
                false
        );

        sportCourtRepository
                .save(
                        court
                );

    }

}