package com.self.music.service;

import com.self.music.authentication.token.JwtTokenProvider;
import com.self.music.domain.RefreshTokenRedisRepo;
import com.self.music.dto.request.TokenReissueRequest;
import com.self.music.dto.response.JwtResponse;
import com.self.music.exception.token.TokenErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static com.self.music.common.util.Preconditions.validate;

@Service
@RequiredArgsConstructor
public class DefaultAuthenticationService implements AuthenticationService{
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRedisRepo refreshTokenRedisRepo;

    @Override
    public JwtResponse reissue(TokenReissueRequest req) {
        Authentication authentication = jwtTokenProvider.getAuthentication(req.accessToken());

        boolean hasRefreshToken = refreshTokenRedisRepo.findByToken(req.refreshToken()).isEmpty();
        validate(
                !hasRefreshToken,
                TokenErrorCode.NO_SUCH_REFRESH_TOKEN
        );

        return jwtTokenProvider.generateToken(authentication);
    }
}
