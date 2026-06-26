package com.microservice.booking.service;

import com.microservice.booking.client.AuthClient;
import com.microservice.booking.client.CourtClient;
import com.microservice.booking.client.CourtResponse;
import com.microservice.booking.client.CustomerResponse;
import com.microservice.booking.entity.Booking;
import com.microservice.booking.entity.BookingStatus;
import com.microservice.booking.entity.PendingBooking;
import com.microservice.booking.rabbit.BookingProducer;
import com.microservice.booking.repository.BookingRepository;
import com.microservice.booking.repository.PendingBookingRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class PendingBookinService {


    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CourtClient courtClient;

    @Autowired
    private AuthClient authClient;
    @Autowired
    private BookingProducer bookingProducer;
    @Autowired
    private PendingBookingRepository pendingRepository;

    @Transactional
    public void processPending() {

        List<PendingBooking> pendings = pendingRepository.findByProcessedFalse();

        for (PendingBooking pending : pendings) {
            try {
                CourtResponse court = courtClient.getCourt(pending.getSportCourtId());
                CustomerResponse customer = authClient.getCustomer(pending.getCustomerAccountId());
                boolean exists = bookingRepository.existsBySportCourtIdAndDateAndStartTime(
                        pending.getSportCourtId(), pending.getDate(), pending.getStartTime());
                if (exists) {
                    pending.setProcessed(true);
                    pendingRepository.save(pending);
                    continue;
                }

                Booking booking = new Booking();
                booking.setSportCourtId(pending.getSportCourtId());
                booking.setCustomerAccountId(pending.getCustomerAccountId());
                booking.setDate(pending.getDate());
                booking.setStartTime(pending.getStartTime());
                booking.setEndTime(pending.getEndTime());
                booking.setPrice(pending.getPrice());
                booking.setQrCode(UUID.randomUUID().toString());
                booking.setStatus(BookingStatus.PENDING);
                Booking saved = bookingRepository.save(booking);

                pending.setProcessed(true);
                pendingRepository.save(pending);
                bookingProducer.sendBookingCreated(
                        "Reserva procesada ID=" + saved.getIdBooking()
                );
            } catch (Exception ex) {
                System.out.println("No se pudo procesar pendiente " + pending.getId());
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PendingBooking save(PendingBooking pending) {
        return pendingRepository.save(pending);
    }
}