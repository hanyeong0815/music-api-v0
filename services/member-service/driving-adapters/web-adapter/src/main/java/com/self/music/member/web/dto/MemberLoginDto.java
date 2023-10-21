package com.self.music.member.web.dto;

import lombok.Builder;

public record MemberLoginDto() {
    @Builder
    public record MemberLoginResponseDto(
            String username,
            String accessToken
    ) {}
}
