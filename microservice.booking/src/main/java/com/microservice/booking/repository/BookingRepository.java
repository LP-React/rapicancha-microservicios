package com.microservice.booking.repository;


import com.microservice.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public interface BookingRepository
        extends JpaRepository<
        Booking,
        Integer> {

    boolean existsBySportCourtIdAndDateAndStartTime(

            Integer courtId,

            LocalDate date,

            LocalTime startTime

    );

    List<Booking>
    findByCustomerAccountIdOrderByDateDesc(

            Integer customerId

    );

    Optional<Booking>
    findByQrCode(   String qrCode);

    List<Booking>
    findBySportCourtIdOrderByDateAscStartTimeAsc(Integer courtId);

}