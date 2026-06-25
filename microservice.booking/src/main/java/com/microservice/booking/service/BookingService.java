package com.microservice.booking.service;

import com.microservice.booking.client.AuthClient;
import com.microservice.booking.client.CourtClient;
import com.microservice.booking.client.CourtResponse;
import com.microservice.booking.client.CustomerResponse;
import com.microservice.booking.dto.BookingRequest;
import com.microservice.booking.dto.BookingResponse;
import com.microservice.booking.dto.CheckInRequest;
import com.microservice.booking.entity.Booking;
import com.microservice.booking.entity.BookingStatus;
import com.microservice.booking.rabbit.BookingProducer;
import com.microservice.booking.repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CourtClient courtClient;

    @Autowired
    private AuthClient authClient;

    @Autowired
    private BookingProducer bookingProducer;

    @Transactional
    public BookingResponse createBooking(BookingRequest request) {

        CourtResponse court = courtClient.getCourt(request.getSportCourtId());
        CustomerResponse customer = authClient.getCustomer(request.getCustomerAccountId());

        boolean exists = bookingRepository.existsBySportCourtIdAndDateAndStartTime(request.getSportCourtId(), request.getDate(), request.getStartTime());

        if (exists) {
            throw new RuntimeException("Horario ocupado");
        }

        Booking booking = new Booking();
        booking.setSportCourtId(request.getSportCourtId());
        booking.setCustomerAccountId(request.getCustomerAccountId());
        booking.setDate(request.getDate());
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setPrice(request.getPrice());
        booking.setQrCode(UUID.randomUUID().toString());

        booking.setStatus(BookingStatus.PENDING);

        Booking saved = bookingRepository.save(booking);

        bookingProducer.sendBookingCreated(
                "Reserva creada ID="
                        + saved.getIdBooking()
                        + ", Cliente="
                        + customer.getFirstName()
                        + " "
                        + customer.getLastName()
                        + ", Cancha="
                        + court.getName()
        );

        return convertToResponse(saved, court, customer);
    }

    private BookingResponse convertToResponse(Booking booking, CourtResponse court, CustomerResponse customer) {

        BookingResponse response = new BookingResponse();

        response.setIdBooking(booking.getIdBooking());
        response.setIdSportCourt(booking.getSportCourtId());
        response.setCourtName(court.getName());
        response.setCustomerName(customer.getFirstName() + " " + customer.getLastName());
        response.setDate(booking.getDate());
        response.setStartTime(booking.getStartTime());
        response.setEndTime(booking.getEndTime());
        response.setPrice(booking.getPrice());
        response.setStatus(booking.getStatus().name());
        response.setQrCode(booking.getQrCode());

            return response;

        }

    @Transactional
    public BookingResponse checkIn(CheckInRequest request) {

        Booking booking = bookingRepository.findByQrCode(request.getQrCode()).orElseThrow(() -> new RuntimeException("QR inválido"));

        if (booking.getStatus() == BookingStatus.CONFIRMED) {
            throw new RuntimeException("Reserva ya validada");
        }

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Reserva cancelada");
        }

        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setCheckedInAt(LocalDateTime.now());

        Booking saved = bookingRepository.save(booking);

        CourtResponse court = courtClient.getCourt(saved.getSportCourtId());
        CustomerResponse customer = authClient.getCustomer(saved.getCustomerAccountId());

        return convertToResponse(saved, court, customer);

    }

    public List<BookingResponse> searchBookings(Integer sportCourtId, Integer customerId) {

        List<Booking> bookings;
        if (sportCourtId != null) {
            bookings = bookingRepository.findBySportCourtIdOrderByDateAscStartTimeAsc(sportCourtId);
        }

        else if(customerId!=null){
            bookings = bookingRepository.findByCustomerAccountIdOrderByDateDesc(customerId);
        }

        else{
            bookings = bookingRepository.findAll();
        }

        return bookings
                .stream()
                .map(booking -> {
                    CourtResponse court = courtClient.getCourt(booking.getSportCourtId());
                    CustomerResponse customer = authClient.getCustomer(booking.getCustomerAccountId());
                    return convertToResponse(booking, court, customer);
                }).collect(Collectors.toList());
    }
}