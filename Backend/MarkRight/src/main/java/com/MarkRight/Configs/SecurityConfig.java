package com.MarkRight.Configs;

import com.MarkRight.Filters.GlobalFilterExceptionHandler;
import com.MarkRight.Filters.JwtUsernameAndPasswordFilter;
import com.MarkRight.Filters.JwtVerificationFilter;
import com.MarkRight.Utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig{

    private final JwtUtils jwtUtils;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http ,
                                                   AuthenticationManager authManager ) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login","/sign_up").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new GlobalFilterExceptionHandler(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JwtUsernameAndPasswordFilter(authManager, jwtUtils))
                .addFilterAfter(new JwtVerificationFilter(jwtUtils) , JwtUsernameAndPasswordFilter.class)
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

}
