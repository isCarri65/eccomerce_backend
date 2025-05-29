package com.ecommerce.services.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    public static final String SECRET_KEY = "23j4h23h42j342jk3h4j2h42j4h32jh4j2h342jk4h32j3h42kj4h2j43h2j34h2j2h3j4h324";
    public String getToken(UserDetails user){
        return getToken(new HashMap<>(), user);
    }
    private String getToken(Map<String, Object> extraClaims, UserDetails user){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key getKey(){
       byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getEmailFromToken(String token){
        return getClaim(token, Claims::getSubject);
    }
    private Claims getAllClaims ( String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String email = getEmailFromToken(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }
     private Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
     }

     private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
     }



}
