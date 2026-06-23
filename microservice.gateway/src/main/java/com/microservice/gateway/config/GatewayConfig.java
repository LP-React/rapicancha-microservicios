package com.microservice.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.web.servlet.function.RequestPredicates.path;

@Configuration
public class GatewayConfig {

    @Bean
    RouterFunction<ServerResponse> authRoute() {

        return route("auth-route")
                .route(path("/api/auth/**"), http())
                .route(path("/api/customer/**"), http())
                .route(path("/api/owner/**"), http())
                .route(path("/api/account/**"), http())
                .before(uri("http://localhost:8081"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> venueRoute() {
        return route("venue-route")
                .route(path("/api/venues/**"), http())
                .before(uri("http://localhost:8083"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> sportCourtRoute() {
        return route("sport-court-route")
                .route(path("/api/sport-courts/**"), http())
                .route(path("/api/venues-and-sport-court"), http())

                .before(uri("http://localhost:8083"))
                .build();
    }



    @Bean
    RouterFunction<ServerResponse> bookingRoute() {
        return route("booking-route")
                .route(path("/api/bookings/**"), http())
                .before(uri("http://localhost:8082"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> availabilityRoute() {
        return route("availability-route")
                .route(path("/api/availability/**"), http())
                .before(uri("http://localhost:8083"))
                .build();
    }
}
