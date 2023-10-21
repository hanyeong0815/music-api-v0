package com.self.music.member.web.service;

import com.self.music.member.application.usecase.MemberLoginUseCase;
import com.self.music.member.application.usecase.data.TokenPair;
import com.self.music.member.web.dto.MemberLoginDto.MemberLoginResponseDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLoginProxyService {
    private final MemberLoginUseCase memberLoginUseCase;

    public MemberLoginResponseDto login(Authentication authentication, HttpServletResponse response) {
        TokenPair token = memberLoginUseCase.login(authentication);

        Cookie cookie = new Cookie("refresh_token", token.refreshToken());
        cookie.setMaxAge(2_592_000);
        cookie.setDomain("");
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        return MemberLoginResponseDto.builder()
                .username(authentication.getName())
                .accessToken(token.accessToken())
                .build();
    }
}
