package com.MarkRight.Controller;

import com.MarkRight.Models.User;
import com.MarkRight.Services.UserService;
import com.MarkRight.Utils.JSendResponse;
import com.MarkRight.Utils.JSendResponseBuilder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Data
public class AuthController {
    private final UserService userService;
    @PostMapping("sign_up")
    public ResponseEntity<JSendResponse> signUp(@RequestBody User user){
        return JSendResponseBuilder.build(userService.registerUser(user));
    }
}
