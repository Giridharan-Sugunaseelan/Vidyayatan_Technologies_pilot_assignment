package com.vidyayatantechnologies.assignment.security.configuration;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtTokenProvider {
    @Value("${app.jwt.secretKey}")
    private String secretKey;

    @Value("${app.jwt.expirationTime}")
    private Long expirationTime;

    public String generateToken(Authentication authentication){
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + expirationTime);
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(key())
                .compact();
    }

    public String getUserNameFromToken(String token){
        Claims body = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return body.getSubject();
    }

    public Boolean validateToken(String token){
        Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parse(token);
        return true;
    }
    public Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

}
