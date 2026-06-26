package com.microservice.gateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtAuthenticationFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public ServerResponse filter(ServerRequest request, HandlerFunction<ServerResponse> next) throws Exception {

        System.out.println("================================");
        System.out.println("PATH: " + request.path());

        String authHeader = request.headers().firstHeader(HttpHeaders.AUTHORIZATION);

        System.out.println("HEADER: " + authHeader);

        if (authHeader == null) {
            System.out.println("NO HAY AUTHORIZATION");
            return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!authHeader.startsWith("Bearer ")) {
            System.out.println("NO EMPIEZA CON BEARER");
            return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authHeader.substring(7);

        System.out.println("TOKEN: " + token);

        try {
            Claims claims = Jwts.parser().verifyWith(buildKey()).build().parseSignedClaims(token).getPayload();
            System.out.println("JWT VÁLIDO");
            System.out.println("SUBJECT: " + claims.getSubject());

            return next.handle(request);

        } catch (Exception e) {
            System.out.println("ERROR JWT");
            e.printStackTrace();
            return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private SecretKey buildKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
