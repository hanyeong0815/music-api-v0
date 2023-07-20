package com.self.music.dto.response;

import com.self.music.domain.Board;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record TestResponse(List<Board1> boards1, List<Board1>boards2) {}

