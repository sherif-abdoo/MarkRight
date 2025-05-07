package com.MarkRight.Utils;

import com.MarkRight.Models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.ServletException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Getter
public class JwtUtils {
    @Value("${jwt.access.secret}")
    private String accessSecret;
    @Value("${jwt.access.lifetime.seconds}")
    private int accessLifetime;

    @Value("${jwt.refresh.secret}")
    private String refreshSecret;
    @Value("${jwt.refresh.lifetime.seconds}")
    private int refreshLifetime;

    public String createToken(User user,
                              String issuer , boolean accessToken) throws IOException, ServletException {

        List<String>auths = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Date now = new Date();
        Date expiryDate = accessToken ? new Date(now.getTime() + accessLifetime*1000)
                : new Date(now.getTime() + refreshLifetime*1000);
        String secret = accessToken ? accessSecret : refreshSecret;

        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuer(issuer)
                .withClaim("authorities" ,auths)
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public boolean validateToken(String token,boolean accessToken) {
        try{
            DecodedJWT jwt = getDecodedJWT(token, accessToken);
            return true;
        }catch(JWTVerificationException e){
            return false;
        }
    }
    public DecodedJWT getDecodedJWT(String token,boolean accessToken) {
        String secret = accessToken ? accessSecret : refreshSecret;
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        }catch(JWTVerificationException e){
            throw new JWTVerificationException(e.getMessage());
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            DecodedJWT decodedJWT = getDecodedJWT(token, true);
            return decodedJWT.getSubject(); // username is stored as subject
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Failed to extract username: " + e.getMessage());
        }
    }
}
