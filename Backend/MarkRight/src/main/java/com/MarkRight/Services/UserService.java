package com.MarkRight.Services;

import com.MarkRight.Models.User;
import com.MarkRight.Models.UserRoles;
import com.MarkRight.Repository.UserRepo;
import com.MarkRight.Utils.JSendResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            User user = userRepo.findByUsername(username);
            return user;
        }catch (Exception e){
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    public JSendResponse registerUser(User user) {
        Map<String, Object> response = new HashMap<>();

        if (userRepo.existsByUsername(user.getUsername())) {
            response.put("error", "Username already exists");
            return JSendResponse.fail(response);
        }

        if (userRepo.existsByEmail(user.getEmail())) {
            response.put("error", "Email already exists");
            return JSendResponse.fail(response);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        response.put("message", "User registered successfully");

        return JSendResponse.success(response);
    }
}
