package com.microservice.booking.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String BOOKING_QUEUE ="booking.queue";

    @Bean
    public Queue bookingQueue() {
        return new Queue(BOOKING_QUEUE,true);
    }
}