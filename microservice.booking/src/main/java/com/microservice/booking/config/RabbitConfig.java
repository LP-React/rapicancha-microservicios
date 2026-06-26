package com.microservice.booking.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String BOOKING_QUEUE = "booking.queue";
    public static final String BOOKING_AUDIT_QUEUE = "booking.audit.queue";

    @Bean
    public Queue bookingQueue() {
        return new Queue(BOOKING_QUEUE, true);
    }

    @Bean
    public Queue bookingAuditQueue() {
        return new Queue(BOOKING_AUDIT_QUEUE, true);
    }
}