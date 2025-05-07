package com.MarkRight.Controller;

import com.MarkRight.Models.User;
import com.MarkRight.Services.RefreshTokenService;
import com.MarkRight.Services.UserService;
import com.MarkRight.Utils.CookiesUtils;
import com.MarkRight.Utils.JSendResponse;
import com.MarkRight.Utils.JSendResponseBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Data
public class AuthController {
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    @PostMapping("/sign_up")
    public ResponseEntity<JSendResponse> signUp(@RequestBody User user){
        return JSendResponseBuilder.build(userService.registerUser(user));
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<JSendResponse> refreshToken(
            @CookieValue(value = "refresh_token", required = false) String refreshToken,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        if (refreshToken == null) {
            Map<String, Object> error = Map.of("error", "Refresh token not found in cookies");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(JSendResponse.fail(error));
        }


        String issuer = request.getRequestURL().toString();
        JSendResponse result = refreshTokenService.refreshToken(
                refreshToken,
                issuer,
                response
        );

        return JSendResponseBuilder.build(result);
    }
}
