package com.self.music.member.application.usecase.data;

import lombok.Builder;

@Builder
public record TokenPair(
        String accessToken,
        String refreshToken
) {}
