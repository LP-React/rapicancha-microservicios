package com.microservice.booking.rabbit;

import com.microservice.booking.config.RabbitConfig;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendBookingCreated(String message) {
        rabbitTemplate.convertAndSend(RabbitConfig.BOOKING_QUEUE, message);
    }
}