package com.janchondo.students.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtils {
    @Value("${app.jwt-secret}")
    private String SECRET_KEY;
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
    public Boolean validateToken(String token, UserDetails userDetails){
        String userName = extractUsername(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    private String createToken(Map<String, Object> claims, String subject){
        Date now = new Date(System.currentTimeMillis());
        Date until = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10);
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(until)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
}
