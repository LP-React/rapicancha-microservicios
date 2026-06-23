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

    @Autowired
    private VenueRepository venueRepository;

    public List<SportCourtResponse> getAllSportCourts(Integer venueId) {

        List<SportCourt> courts;

        if (venueId != null) {

            courts = sportCourtRepository
                    .findByVenueIdAndIsActiveTrue(
                            venueId
                    );

        } else {

            courts = sportCourtRepository
                    .findByIsActiveTrue();

        }

        return courts.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

    }

    public SportCourtResponse createSportCourt(
            SportCourtRequest request) {

        SportCourt court = new SportCourt();

        court.setVenueId(
                request.getVenueId()
        );

        mapRequestToEntity(
                request,
                court
        );

        SportCourt saved =
                sportCourtRepository
                        .save(court);
        if (request.getImageUrls() != null) {

            for (String url : request.getImageUrls()) {

                SportCourtImage image =
                        new SportCourtImage();

                image.setSportCourtId(
                        saved.getIdSportCourt()
                );

                image.setImageUrl(url);

                imageRepository.save(image);
            }
        }

        return convertToResponse(
                saved
        );

    }

    public SportCourtResponse updateSportCourt(
            Integer id,
            SportCourtRequest request) {

        SportCourt court =
                sportCourtRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
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
            SportCourt court) {

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

        if (request.getHasRoof() != null) {

            court.setHasRoof(
                    request.getHasRoof()
            );

        }

        if (request.getHasLighting() != null) {

            court.setHasLighting(
                    request.getHasLighting()
            );

        }

        if (request.getSlotMinutes() != null) {

            court.setSlotMinutes(
                    request.getSlotMinutes()
            );

        }

        if (request.getPlayMinutes() != null) {

            court.setPlayMinutes(
                    request.getPlayMinutes()
            );

        }

        if (court.getIsActive() == null) {
            court.setIsActive(true);
        }
    }

    private SportCourtResponse convertToResponse(
            SportCourt court) {

        SportCourtResponse response =
                new SportCourtResponse();

        response.setIdSportCourt(
                court.getIdSportCourt()
        );

        response.setVenueId(
                court.getVenueId()
        );

        if (court.getVenueId() != null) {
            venueRepository.findById(court.getVenueId())
                    .ifPresent(venue -> response.setVenueName(venue.getName()));
        }

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

        response.setRules(
                court.getRules()
        );

        response.setHasRoof(
                court.getHasRoof()
        );

        response.setHasLighting(
                court.getHasLighting()
        );

        response.setSlotMinutes(
                court.getSlotMinutes()
        );

        response.setPlayMinutes(
                court.getPlayMinutes()
        );

        response.setIsActive(
                court.getIsActive()
        );
        List<SportCourtImageResponse> imageResponses =
                imageRepository
                        .findBySportCourtId(
                                court.getIdSportCourt()
                        )
                        .stream()
                        .map(img -> {

                            SportCourtImageResponse dto =
                                    new SportCourtImageResponse();

                            dto.setIdSportCourtImage(
                                    img.getIdSportCourtImage()
                            );

                            dto.setImageUrl(
                                    img.getImageUrl()
                            );

                            return dto;

                        })
                        .toList();

        response.setImages(
                imageResponses
        );
        return response;

    }

    public SportCourtDetailResponse getCourtDetail(
            Integer id) {

        SportCourt court =
                sportCourtRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Cancha no encontrada"
                                )
                        );

        SportCourtResponse baseResponse =
                convertToResponse(
                        court
                );

        SportCourtDetailResponse detail =
                new SportCourtDetailResponse();

        BeanUtils.copyProperties(
                baseResponse,
                detail
        );

        return detail;

    }

    public void deleteSportCourt(
            Integer id) {

        SportCourt court =
                sportCourtRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Cancha no encontrada"
                                )
                        );

        court.setIsActive(
                false
        );

        sportCourtRepository.save(
                court
        );

    }
}