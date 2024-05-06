package com.annodocs.annodocsbackend.core.security;

import com.annodocs.annodocsbackend.user.UserEntity;
import com.annodocs.annodocsbackend.user.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${secret.key}")
    private String secret;
    private final UserRepository userRepository;


    public String getTokenFrom(String bearerToken) {
        final String bearer = "Bearer ";
        if (bearerToken == null || !bearerToken.startsWith(bearer))
            throw new JWTVerificationException("Invalid Authorization Header");
        String token = bearerToken.substring(bearer.length());
        return token;
    }

    public String getSubjectFrom(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);  // throws JWTVerificationException if not valid
        return decodedJWT.getSubject();
    }

    public String generateToken(UserEntity user) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        Instant expiration = generateExpirationTimeIn(10);  // expires in 10 min
        String token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(expiration)
                .withIssuer("annodocs")
                .withClaim("roles",user.getRole().name())
                .sign(algorithm);
        return token;
    }

    @Transactional
    public String generateRefreshToken(UserEntity user) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        Instant expiration = generateExpirationTimeIn(60*24*30);  // expires in 30 days

        // save the expiration time of the newest refresh token
        user.setNewest_refresh_token(expiration);
        userRepository.save(user);

        String token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(expiration)
                .withIssuer("annodocs")
                .withClaim("refresh",true)
                .sign(algorithm);
        return token;
    }

    public String verifyRefreshToken(String token) {
        //get expiration time of token
        DecodedJWT decodedJWT = JWT.decode(token);
        Instant expiration = decodedJWT.getExpiresAt().toInstant();
        String email = decodedJWT.getSubject();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new JWTVerificationException("User not found"));

        // check if the token is the newest refresh token
        if (user.getNewest_refresh_token().isAfter(expiration.plusSeconds(1))) {
            throw new JWTVerificationException("Invalid Refresh Token");
        }

        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT verifiedJWT = verifier.verify(token);  // throws JWTVerificationException if not valid
        if (!verifiedJWT.getClaim("refresh").asBoolean()) {
            throw new JWTVerificationException("Invalid Refresh Token");
        }
        return verifiedJWT.getSubject();
    }

    private Instant generateExpirationTimeIn(int minutes) {
        return LocalDateTime.now().plusMinutes(minutes).atZone(ZoneId.systemDefault()).toInstant();
    }
}
