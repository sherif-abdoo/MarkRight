package com.MarkRight.Filters;

import com.MarkRight.Dto.LoginRequestDto;
import com.MarkRight.Utils.JSendResponse;
import com.MarkRight.Utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
public class JwtUsernameAndPasswordFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        LoginRequestDto credentials;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Authentication auth = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(), credentials.getPassword());

        return authenticationManager.authenticate(auth);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String accessToken = jwtUtils.createAccessToken(authResult,
                request.getRequestURL().toString(),
                true);

        String refreshToken = jwtUtils.createAccessToken(authResult,
                request.getRequestURL().toString(),
                false);

        Cookie accessCookie = new Cookie("access_token", accessToken);
        accessCookie.setPath("/");
        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(true);
        accessCookie.setMaxAge(600);
        response.addCookie(accessCookie);

        Cookie refreshCookie = new Cookie("refresh_token", refreshToken);
        refreshCookie.setPath("/");
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true);
        refreshCookie.setMaxAge(604800);
        response.addCookie(refreshCookie);


        Map<String, Object> data = Map.of(
                "message", "Login successful",
                "username", authResult.getName()
        );

        JSendResponse jsend = JSendResponse.success(data);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        new ObjectMapper().writeValue(response.getWriter(), jsend);

        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> data = Map.of(
                "error", "Invalid username or password"
        );

        JSendResponse jsend = JSendResponse.fail(data);

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");

        new ObjectMapper().writeValue(response.getWriter(), jsend);
    }

}
