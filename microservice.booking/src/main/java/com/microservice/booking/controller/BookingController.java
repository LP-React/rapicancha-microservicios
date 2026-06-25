package com.microservice.booking.controller;

import com.microservice.booking.dto.BookingRequest;
import com.microservice.booking.dto.BookingResponse;
import com.microservice.booking.dto.CheckInRequest;
import com.microservice.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @PostMapping
    public BookingResponse createBooking(@RequestBody BookingRequest request) {
        return service.createBooking(request);
    }

    @PostMapping("/checkin")
    public BookingResponse checkIn(@RequestBody CheckInRequest request) {
        return service.checkIn(request);
    }

    @GetMapping
    public List<BookingResponse> searchBookings(@RequestParam(required = false) Integer sportCourtId, @RequestParam(required = false) Integer customerId) {
        return service.searchBookings(sportCourtId, customerId);
    }
}