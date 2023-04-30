package com.applicantportal.ApplicantPortal.jwtFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenUtil {
    @Getter
    private static final String SECRET_KEY = "2B4D6251655468576D5A7134743777217A25432A462D4A404E635266556A586E";
    private static final long JWT_EXPIRATION = 1000 * 60 * 24;

    public String generateToken(Map<String, Object> extraClaims,
                                UserDetails userDetails){
        String compact = Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION)) // 1000 millisecond = 1second therefore expiration is 1*60*24 = 24hours
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
        System.out.println(compact);
        return compact;
    }
        private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
        public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return(username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public <T>T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT: " + e.getMessage());
        }
        return null;
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private boolean isTokenExpired(String token){
        return getExpirationDateFromToken(token).before(new Date());
    }
    private Date getExpirationDateFromToken(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

}
