package com.self.music.authentication;

import com.self.music.utills.token.CommonAuthenticationToken;
import com.self.music.utills.token.UserAuthenticationToken;
import com.self.music.dto.request.LoginDto.LoginRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public UserAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        setRequiresAuthenticationRequestMatcher(new OrRequestMatcher(new AntPathRequestMatcher("/login")));

        setAuthenticationSuccessHandler(((request, response, authentication) -> {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            }
        }));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username;
        String password;
        try {
            String request_body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
            LoginRequest loginRequest = LoginRequest.ConvertFromString(request_body);
            username = loginRequest.getUsername().trim();
            password = (loginRequest.getPassword() != null) ? loginRequest.getPassword() : "";
        } catch (Exception e) {
            throw new BadCredentialsException("invalid credential request");
        }
        Authentication authentication;
        try {
            authentication = CommonAuthenticationToken.unauthenticated(
                    UserAuthenticationToken.class, username, password
            );
        } catch (Exception e) {
            throw new AuthenticationServiceException("occurred a problem while creating token");
        }
        return this.getAuthenticationManager().authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }
}
