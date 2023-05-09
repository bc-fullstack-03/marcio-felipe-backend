package com.sysMapBootCamp.projectSysMapBootCamp.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtService implements IJwtService {


    private final long EXPIRATION_TIME = 7200000;
    private final String KEY = "28472B4B6250655368566B5970337336763979244226452948404D635166546A";

    public String generateToken(UUID user_id) {

        return Jwts
                .builder()
                .setSubject(user_id.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(genSigInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValid(String token, String user_id) {

        var sub = getClaim(token, Claims::getSubject);
        var tExpiration = getClaim(token, Claims::getExpiration);

        if (!sub.equals(user_id)) {
            return false;
        }

        if (tExpiration.before(new Date())) {
            return false;
        }

        return true;
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        var claims = Jwts
                .parserBuilder()
                .setSigningKey(genSigInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claimsResolver.apply(claims);
    }


    private Key genSigInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY));
    }
}
