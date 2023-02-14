package com.example.NewJeans.security;

import com.example.NewJeans.Entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@Slf4j
public class TokenProvider {

    private static final String SECRET_KEY="Q4NSl604sgyHJj1qwEkRasdUeR4uUAt7WJraD7EN3O9DVccyYuHxMEbuuuXXyYJkal13eqgB0F7Bq4Hwoksdvon123ovb";

    public String createToken(Member member){

        Date expireDate=Date.from(
                Instant.now()
                        .plus(1, ChronoUnit.DAYS)
        );

        return Jwts.builder()

                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()),
                        SignatureAlgorithm.HS512)
                .setSubject(member.getMemID().toString())
                .setIssuer("todo app")
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .compact();
    }


    public String validateANdGetUserId(String token){

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
    public String getSubject(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }
    public  boolean validatedToken(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).build().parseClaimsJws(token).getBody();
        if(claims !=null){
            return  true;
        }else{
            return  false;
        }
    }



}