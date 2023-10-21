package com.self.music.member.application.repository;

import com.self.music.member.domain.RefreshToken;
import org.springframework.stereotype.Repository;

public interface RefreshTokenRepository {
    RefreshToken save(RefreshToken refreshToken);
}
