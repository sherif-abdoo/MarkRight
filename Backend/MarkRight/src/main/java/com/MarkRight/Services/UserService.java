package com.MarkRight.Services;

import com.MarkRight.Models.User;
import com.MarkRight.Repository.UserRepo;
import com.MarkRight.Utils.EmailValidator;
import com.MarkRight.Utils.JSendResponse;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final EmailValidator emailValidator;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public JSendResponse registerUser(User user) {
        Map<String, Object> response = new HashMap<>();


        if(Strings.isNullOrEmpty(user.getUsername()) ||
                Strings.isNullOrEmpty(user.getPassword()) ||
                Strings.isNullOrEmpty(user.getEmail())) {
            response.put("error", "All the fields are required");
            return JSendResponse.fail(response);
        }

        if (userRepo.existsByUsername(user.getUsername())) {
            response.put("error", "Username already exists");
            return JSendResponse.fail(response);
        }

        if (userRepo.existsByEmail(user.getEmail())) {
            response.put("error", "Email already exists");
            return JSendResponse.fail(response);
        }

        if(!emailValidator.test(user.getEmail())) {
            response.put("error", "Wrong email format");
            return JSendResponse.fail(response);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        response.put("message", "User registered successfully");

        return JSendResponse.success(response);
    }
}
