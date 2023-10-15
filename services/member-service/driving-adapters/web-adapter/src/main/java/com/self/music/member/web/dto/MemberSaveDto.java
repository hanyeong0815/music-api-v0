package com.self.music.member.web.dto;

import lombok.Builder;

import java.util.List;

public record MemberSaveDto() {
    public record MemberSaveRequestDto(
            String username,
            String password,
            List<String> roles
    ) {}

    @Builder
    public record MemberSaveResponseDto(
            String username,
            List<String> roles
    ) {}
}
