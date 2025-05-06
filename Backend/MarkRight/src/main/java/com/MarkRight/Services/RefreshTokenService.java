package com.MarkRight.Services;

import com.MarkRight.Models.User;
import com.MarkRight.Utils.JSendResponse;
import com.MarkRight.Utils.JwtUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Service
@AllArgsConstructor
public class RefreshTokenService {
    private final JwtUtils jwtUtils;
    private final UserService userService;
    public JSendResponse refreshToken(String refreshToken ,
                                      String issuer ,
                                      HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> Jresponse = new HashMap<>();
        if(!jwtUtils.validateToken(refreshToken,false)){
            Jresponse.put("error", "Invalid refresh token");
            return JSendResponse.fail(Jresponse);
        }
        DecodedJWT decodedJWT = jwtUtils.getDecodedJWT(refreshToken,false);
        String username = decodedJWT.getSubject();
        User user = (User) userService.loadUserByUsername(username);
        String newAccessToken = jwtUtils.createToken(user,issuer , true);
        Cookie cookie = new Cookie("access_token",newAccessToken);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(600);
        response.addCookie(cookie);
        Jresponse.put("message", "Refreshed Successfully!");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        return JSendResponse.success(Jresponse);
    };
}
