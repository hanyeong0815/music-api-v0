package com.self.music.authentication;

import com.self.music.exception.user.UserErrorCode;
import com.self.music.utills.token.CommonAuthenticationToken;
import com.self.music.utills.token.UserAuthenticationToken;
import com.self.music.service.CustomUserDetailsService;
import com.self.music.utills.password.PasswordEncoderFactory;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static com.self.music.common.util.Preconditions.validate;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationProvider {


    private final CustomUserDetailsService defaultUserService;
    private final PasswordEncoderFactory pwEncoder;

    @Override
    public Authentication authenticate(@NotNull Authentication authentication) throws AuthenticationException {
        UserDetails user = defaultUserService.loadUserByUsername(authentication.getName());

        validate(
                user != null,
                UserErrorCode.NO_SUCH_USER
        );

        boolean isAuthenticated = pwEncoder.defaultEncoder().matches((String)authentication.getCredentials(), user.getPassword());

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
