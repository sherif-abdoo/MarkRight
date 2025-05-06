package com.MarkRight.Filters;

import com.MarkRight.Utils.CookiesUtils;
import com.MarkRight.Utils.JwtUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.base.Strings;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth0.jwt.algorithms.Algorithm;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();
        if (uri.contains("/sign_up") || uri.contains("/refresh-token")) {
            System.out.println("refresh token");
            filterChain.doFilter(request, response);
            return;
        }

        String token = CookiesUtils.extractFromCookies(request, "access_token");

        if (Strings.isNullOrEmpty(token)) {
            throw new ServletException("Missing or invalid access token in cookies");
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtUtils.getAccessSecret());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);

            String subject = jwt.getSubject();
            List<String> authorities = jwt.getClaim("authorities").asList(String.class);

            List<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            Authentication auth = new UsernamePasswordAuthenticationToken(subject, null, grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (JWTVerificationException e) {
            throw new IllegalStateException("Invalid JWT token: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }


}
