package com.self.music.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public record JwtResponse(Long usr_id, String username, String access_token, String refresh_token, Long expired_in,
                          Long refresh_expired_in, String token_type) {
}
