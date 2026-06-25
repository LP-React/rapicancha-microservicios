package com.microservice.booking.rabbit;

import com.microservice.booking.config.RabbitConfig;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BookingConsumer {

    @RabbitListener(queues = RabbitConfig.BOOKING_QUEUE)
    public void receiveMessage(String message) {
        System.out.println("RABBITMQ -> " + message);
    }
}