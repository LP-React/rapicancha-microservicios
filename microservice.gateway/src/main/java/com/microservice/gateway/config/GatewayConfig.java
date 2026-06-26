package com.microservice.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.web.servlet.function.RequestPredicates.path;
import static org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions.circuitBreaker;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.HashMap;

@Configuration
public class GatewayConfig {

    @Bean
    RouterFunction<ServerResponse> authPublicRoute() {
        return route("auth-public-route")
                .route(path("/api/auth/**"), http())
                .before(uri("http://localhost:8081"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> authProtectedRoute(JwtAuthenticationFilter jwtFilter) {
        return route("auth-protected-route")
                .route(path("/api/customer/**"), http())
                .route(path("/api/owner/**"), http())
                .route(path("/api/account/**"), http())
                .filter(jwtFilter)
                .before(uri("http://localhost:8081"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> venueRoute(JwtAuthenticationFilter jwtFilter) {
        return route("venue-route")
                .route(path("/api/venues/**"), http())
                .filter(jwtFilter)
                .before(uri("http://localhost:8083"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> sportCourtRoute(JwtAuthenticationFilter jwtFilter) {
        return route("sport-court-route")
                .route(path("/api/sport-courts/**"), http())
                .route(path("/api/venues-and-sport-court"), http())
                .filter(jwtFilter)
                .filter(circuitBreaker("courtCB", "/fallback/court"))
                .before(uri("http://localhost:8083"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> bookingRoute(JwtAuthenticationFilter jwtFilter) {
        return route("booking-route")
                .route(path("/api/bookings/**"), http())
                .filter(jwtFilter)
                .filter(circuitBreaker("bookingCB", "/fallback/booking"))
                .before(uri("http://localhost:8082"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> availabilityRoute(JwtAuthenticationFilter jwtFilter) {
        return route("availability-route")
                .route(path("/api/availability/**"), http())
                .filter(jwtFilter)
                .before(uri("http://localhost:8083"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> fallbackRoute() {
        return route("fallback-route")
                .route(path("/fallback/**"), request -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("error", "Servicio no disponible");
                    response.put("message", "El servicio no está disponible temporalmente, intente más tarde.");
                    response.put("status", 503);
                    return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
                })
                .build();
    }
}
