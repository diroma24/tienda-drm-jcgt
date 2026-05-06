package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${project.api.jwt.secret}")
    private String secretKey;

    @Value("${project.api.jwt.access-expiration}")
    private long accessExpiration;

    @Value("${project.api.jwt.refresh-expiration}")
    private long refreshExpiration;

    @Override
    public String generateAccessToken(UserDetails user) {
        return buildToken(new HashMap<>(), user, accessExpiration, TokenType.ACCESS);
    }

    @Override
    public String generateRefreshToken(UserDetails user) {
        return buildToken(new HashMap<>(), user, refreshExpiration, TokenType.REFRESH);
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails user, long expiration, TokenType type) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername()) // Claim "Subject"
                .setIssuedAt(new Date(System.currentTimeMillis())) // Claim "issuedAt"
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Claim "expiration"
                .claim("type", type.name()) // Claim personalizado "type"
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Firma digital[cite: 1]
                .compact();
    }
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Boolean isTokenValid(String token, UserDetails user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }

    @Override
    public Boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignInKey()) // Valida la firma al extraer[cite: 1]
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
