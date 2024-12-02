package com.ems.Service.Impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ems.Entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expirytime}")
    private int expirytime;
    private Algorithm algorithm;

    @PostConstruct
    public void PostConstruct(){

        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    private static final String USER_NAME ="username";

    public String generateJWTToken(User user){
        return JWT.create()
                .withClaim(USER_NAME,user.getUsername())
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis()+expirytime))
                .sign(algorithm);

    }
    public String getbyUsername(String token){
        DecodedJWT decodejwt = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        return decodejwt.getClaim(USER_NAME).asString();
    }


}
