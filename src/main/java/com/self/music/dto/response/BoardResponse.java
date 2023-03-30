package com.self.music.dto.response;

import com.self.music.domain.Board;

import java.time.Instant;
import java.util.Optional;

public record BoardResponse (String id, Long userId, String title, String lyrics, String musicUrl, String imgUrl, Instant uploadDate) {
}
