package com.gateway.nimble.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenValidationService {

    @Value("${security.token.jwt.secret}")
    private String secret;

    public Optional<String> isValid(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return Optional.of(JWT.require(algorithm)
                    .withIssuer("nimble-pagamento")
                    .build()
                    .verify(token)
                    .getSubject());
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }
}


//TODO: Validar e Melhorar logica