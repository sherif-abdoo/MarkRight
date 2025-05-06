package com.MarkRight.Utils;

import com.MarkRight.Models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class JwtUtils {
    @Value("${jwt.access.secret}")
    private String accessSecret;
    @Value("${jwt.access.lifetime.seconds}")
    private int accessLifetime;

    @Value("${jwt.refresh.secret}")
    private String refreshSecret;
    @Value("${jwt.refresh.lifetime.seconds}")
    private int refreshLifetime;

    public String createAccessToken(Authentication authResult,
                                    String issuer , boolean access) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();

        List<String>auths = authResult.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities" ,auths);

        Date now = new Date();
        Date expiryDate = access ? new Date(now.getTime() + accessLifetime*1000)
                : new Date(now.getTime() + refreshLifetime*1000);
        String secret = access ? accessSecret : refreshSecret;

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer(issuer)
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
