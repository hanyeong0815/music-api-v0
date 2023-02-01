package com.self.music.authentication;

import com.self.music.authentication.token.CommonAuthenticationToken;
import com.self.music.authentication.token.UserAuthenticationToken;
import com.self.music.service.DefaultUserService;
import com.self.music.utills.PasswordEncoderStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {
    private final DefaultUserService defaultUserService;
    private final PasswordEncoderStorage pwEncoders;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails user = defaultUserService.loadUserByUsername(authentication.getName());

        boolean isAuthenticated = pwEncoders.getPasswordEncoder().matches((String)authentication.getCredentials(), user.getPassword());

        if (isAuthenticated) {
            authentication = CommonAuthenticationToken.authenticated(UserAuthenticationToken.class, authentication.getName(), user, user.getAuthorities());
            ((CommonAuthenticationToken)authentication).eraseCredentials();
            return authentication;
        } else throw new BadCredentialsException("wrong authentication information");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
