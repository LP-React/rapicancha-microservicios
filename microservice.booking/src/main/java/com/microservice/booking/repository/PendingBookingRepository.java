package com.microservice.booking.repository;

import com.microservice.booking.entity.PendingBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PendingBookingRepository extends JpaRepository<PendingBooking,Integer> {

    List<PendingBooking> findByProcessedFalse();

}