package com.self.music.dto.request;

import lombok.Builder;

@Builder
public record TokenReissueRequest(String accessToken, String refreshToken) {
}
