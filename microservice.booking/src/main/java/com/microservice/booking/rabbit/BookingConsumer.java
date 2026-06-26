package com.microservice.booking.rabbit;

import com.microservice.booking.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BookingConsumer {

   @RabbitListener(
            queues = RabbitConfig.BOOKING_QUEUE
    )
    public void receiveMessage(String message) {

        System.out.println(
                "====================================="
        );

        System.out.println(
                "RABBITMQ -> " + message
        );

        if(message.contains("pendiente")){

            System.out.println(
                    "Esperando reprocesamiento..."
            );

        }

        if(message.contains("procesada")){

            System.out.println(
                    "Reserva sincronizada correctamente."
            );

        }

        System.out.println(
                "====================================="
        );

    }

}