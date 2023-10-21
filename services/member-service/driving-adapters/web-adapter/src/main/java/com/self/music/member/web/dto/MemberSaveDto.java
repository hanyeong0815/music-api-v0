package com.self.music.member.web.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.util.List;

import static com.self.music.member.domain.policy.Members.InvalidationMessage.PASSWORD_MESSAGE;
import static com.self.music.member.domain.policy.Members.InvalidationMessage.USERNAME_MESSAGE;
import static com.self.music.member.domain.policy.Members.Validation.PASSWORD;
import static com.self.music.member.domain.policy.Members.Validation.USERNAME;

public record MemberSaveDto() {
    public record MemberSaveRequestDto(
            @NotBlank
            @Pattern(regexp = USERNAME, message = USERNAME_MESSAGE)
            String username,
            @NotBlank
            @Pattern(regexp = PASSWORD, message = PASSWORD_MESSAGE)
            String password,
            List<String> roles
    ) {}

    @Builder
    public record MemberSaveResponseDto(
            String username,
            List<String> roles
    ) {}
}
