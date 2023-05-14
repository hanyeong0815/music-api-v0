package com.self.music.dto.response;

import lombok.*;

import java.util.Date;

@Builder
public record JwtResponse(Long usr_id, String username, String access_token, String refresh_token, Date expired_in,
                          Date refresh_expired_in, String token_type) {
}
