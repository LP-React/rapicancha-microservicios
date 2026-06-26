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
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PendingBookinService {

    private final BookingRepository bookingRepository;
    private final CourtClient courtClient;
    private final AuthClient authClient;
    private final BookingProducer bookingProducer;
    private final PendingBookingRepository pendingRepository;

    @Transactional
    public void processPending() {
        List<PendingBooking> pendings = pendingRepository.findByProcessedFalse();

        for (PendingBooking pending : pendings) {
            try {
                CourtResponse court = courtClient.getCourt(pending.getSportCourtId());
                CustomerResponse customer = authClient.getCustomer(pending.getCustomerAccountId());

                if (court.getName().contains("Resiliencia") ||
                        (customer.getLastName() != null && customer.getLastName().contains("Resiliencia"))) {
                    System.out.println("Servicios aún no disponibles, omitiendo pendiente ID: " + pending.getId());
                    continue;
                }

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
                        "Reserva procesada exitosamente ID=" + saved.getIdBooking() +
                                " tras recuperación del sistema."
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