package com.self.music.member.web.dto;

import lombok.Builder;

public record MemberSaveDto() {
    public record MemberSaveRequestDto(
            String username,
            String password
    ) {}

    @Builder
    public record MemberSaveResponseDto(
            String username
    ) {}
}
