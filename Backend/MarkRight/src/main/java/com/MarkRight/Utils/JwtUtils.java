package com.MarkRight.Utils;

import com.MarkRight.Models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.ServletException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
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

    public String createAccessToken(Authentication authResult,
                                    String issuer , boolean access) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();

        List<String>auths = authResult.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Date now = new Date();
        Date expiryDate = access ? new Date(now.getTime() + accessLifetime*1000)
                : new Date(now.getTime() + refreshLifetime*1000);
        String secret = access ? accessSecret : refreshSecret;

        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuer(issuer)
                .withClaim("authorities" ,auths)
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC256(secret));
    }
}
