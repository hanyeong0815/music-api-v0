package com.self.music.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record Board1(String id, Long userId, Instant uploadDate) {
}
