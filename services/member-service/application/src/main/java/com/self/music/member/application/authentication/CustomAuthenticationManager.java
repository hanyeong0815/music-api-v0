package com.self.music.member.application.authentication;

import com.self.music.member.application.authentication.utils.CommonAuthenticationToken;
import com.self.music.member.application.authentication.utils.UserAuthenticationToken;
import com.self.music.member.application.exception.MemberErrorCode;
import com.self.music.member.application.usecase.MemberAuthenticateUseCase;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.self.music.common.utils.exception.Preconditions.validate;


@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationProvider {


    private final MemberAuthenticateUseCase defaultUserService;
    private final PasswordEncoder pwEncoder;

    @Override
    public Authentication authenticate(@NotNull Authentication authentication) throws AuthenticationException {
        UserDetails user = defaultUserService.loadUserByUsername(authentication.getName());

        validate(
                user == null,
                MemberErrorCode.NO_SUCH_USER
        );

        boolean isAuthenticated = pwEncoder.matches((String) authentication.getCredentials(), user.getPassword());

        validate(
                !isAuthenticated,
                MemberErrorCode.INVALID_USERNAME_OR_PASSWORD
        );

        authentication = CommonAuthenticationToken
                .authenticated(
                        UserAuthenticationToken.class,
                        authentication.getName(),
                        user,
                        user.getAuthorities()
                );
        ((CommonAuthenticationToken)authentication).eraseCredentials();
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
