package com.microservice.court.service;

import com.microservice.court.dto.*;
import com.microservice.court.entity.Venue;
import com.microservice.court.feign.AuthClient;
import com.microservice.court.repository.SportCourtRepository;
import com.microservice.court.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final AuthClient authClient;
    private final VenueRepository venueRepository;
    private final SportCourtRepository sportCourtRepository;

    public List<VenueResponse> getAllVenues(Integer ownerId) {
        List<Venue> venues;

        if(ownerId != null){
            venues = venueRepository.findByOwnerAccountId(ownerId);

        }else {
            venues = venueRepository.findAll();
        }

        return venues
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public VenueResponse createVenue(VenueRequest request){

        Venue venue= new Venue();
        venue.setOwnerAccountId(request.getOwnerAccountId());

        mapRequestToEntity(request, venue);

        return convertToResponse(venueRepository.save(venue));
    }

    private void mapRequestToEntity(VenueRequest request, Venue venue){
        venue.setName(request.getName());
        venue.setAddress(request.getAddress());
        venue.setLatitude(request.getLatitude());
        venue.setLongitude(request.getLongitude());
        venue.setDescription(request.getDescription());
        venue.setBannerImageUrl(request.getBannerImageUrl());
        venue.setProvidesEquipment(request.getProvidesEquipment()!=null ? request.getProvidesEquipment() : false);
        venue.setOpenTime(request.getOpenTime());
        venue.setCloseTime(request.getCloseTime());
        venue.setMaxCapacity(request.getMaxCapacity());
        venue.setParkingCapacity(request.getParkingCapacity()!=null ? request.getParkingCapacity() : 0);
        venue.setHasParking(request.getHasParking()!=null ? request.getHasParking() : false);
        venue.setHasLockerRoom(request.getHasLockerRoom()!=null ? request.getHasLockerRoom() : false);
        venue.setHasRestroom(request.getHasRestroom()!=null ? request.getHasRestroom() : true);
        venue.setHasStore(request.getHasStore()!=null ? request.getHasStore() : false);
        venue.setHasShower(request.getHasShower()!=null ? request.getHasShower() : false);
        venue.setProvidesBalls(request.getProvidesBalls()!=null ? request.getProvidesBalls() : false);
        venue.setProvidesBibs(request.getProvidesBibs()!=null ? request.getProvidesBibs() : false);
    }

    private VenueResponse convertToResponse(Venue venue){
        VenueResponse response= new VenueResponse();

        response.setIdVenue(venue.getIdVenue());
        response.setName (venue.getName());
        response.setOwnerAccountId(venue.getOwnerAccountId());

        OwnerResponse owner= authClient.getOwner(venue.getOwnerAccountId());

        response.setOwnerName(owner.getFirstName() +" " +owner.getLastName());
        response.setAddress(venue.getAddress());
        response.setLatitude(venue.getLatitude());
        response.setLongitude(venue.getLongitude());
        response.setDescription(venue.getDescription());
        response.setBannerImageUrl(venue.getBannerImageUrl());
        response.setProvidesEquipment(venue.getProvidesEquipment());
        response.setOpenTime(venue.getOpenTime());
        response.setCloseTime(venue.getCloseTime());
        response.setMaxCapacity(venue.getMaxCapacity());
        response.setParkingCapacity(venue.getParkingCapacity());
        response.setHasParking(venue.getHasParking());
        response.setHasLockerRoom(venue.getHasLockerRoom());
        response.setHasRestroom(venue.getHasRestroom());
        response.setHasStore(venue.getHasStore());
        response.setHasShower(venue.getHasShower());
        response.setProvidesBalls(venue.getProvidesBalls());
        response.setProvidesBibs(venue.getProvidesBibs());
        response.setIs_active(venue.getIs_active());

        Long totalCourts = sportCourtRepository.countByVenueIdAndIsActiveTrue(venue.getIdVenue());

        response.setTotalCourts(totalCourts.intValue());

        return response;
    }

    public VenueResponse getVenueById(Integer id){
        Venue venue= venueRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Local no encontrado")
        );

        return convertToResponse(venue);
    }

    public List<VenueWithCourtsResponse> getVenuesAndCourtsByOwner(Integer ownerId){

        List<Venue> venues = venueRepository.findByOwnerAccountId(ownerId);

        return venues.stream()
                .map(venue -> {
                    VenueWithCourtsResponse response = new VenueWithCourtsResponse();

                    response.setIdVenue(venue.getIdVenue());
                    response.setName(venue.getName());

                    List<SportCourtSimpleResponse> courts = sportCourtRepository.findByVenueIdAndIsActiveTrue(venue.getIdVenue())
                                    .stream()
                                    .map(court -> {
                                        SportCourtSimpleResponse dto = new SportCourtSimpleResponse();
                                        dto.setIdSportCourt(court.getIdSportCourt());
                                        dto.setName(court.getName());

                                        return dto;
                                    })
                                    .toList();

                    response.setSportCourts(courts);
                    return response;
                })
                .toList();
    }


    public VenueResponse updateVenue(Integer id, VenueRequest request){
        Venue venue= venueRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Local no encontrado"));

        mapRequestToEntity(request, venue);

        return convertToResponse(venueRepository.save(venue));
    }

    public void deleteVenue(Integer id){
        Venue venue= venueRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Local no encontrado"));

        venue.setIs_active(false);

        venueRepository.save(venue);
    }
}