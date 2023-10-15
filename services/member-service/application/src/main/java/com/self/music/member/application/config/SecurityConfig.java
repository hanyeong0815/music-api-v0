package com.self.music.member.application.config;

import com.self.music.member.application.authentication.CustomAuthenticationManager;
import com.self.music.member.application.authentication.UserAuthenticationFilter;
import com.self.music.member.application.authentication.token.JwtAuthenticationFilter;
import com.self.music.member.application.authentication.token.JwtTokenProvider;
import com.self.music.passwordEncoder.PasswordEncoderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final UserAuthenticationFilter authenticationFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    SecurityConfig(CustomAuthenticationManager userAuthenticationProvider, JwtTokenProvider jwtTokenProvider) {
        jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenProvider);
        AuthenticationManager providerManager = new ProviderManager(userAuthenticationProvider);
        authenticationFilter = new UserAuthenticationFilter(providerManager);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder(PasswordEncoderFactory passwordEncoderFactory) {
        return passwordEncoderFactory.createBcrypt(12);
    }
}
