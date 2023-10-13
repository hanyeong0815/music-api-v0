package com.self.music.dto.response;

import lombok.*;

import java.util.Date;

@Builder
public record JwtResponse(
        String username,
        String access_token,
        String token_type) {
}
