package com.self.music.redis.repository;

import com.self.music.member.application.repository.RefreshTokenRepository;
import com.self.music.member.domain.RefreshToken;
import com.self.music.redis.entity.RefreshTokenEntity;
import com.self.music.redis.mapper.RefreshTokenEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenPersistence implements RefreshTokenRepository {
    private final RefreshTokenRedisRepository repository;
    private final RefreshTokenEntityMapper mapper;
    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        RefreshTokenEntity refreshTokenEntity = mapper.toEntity(refreshToken);
        return mapper.toDomain(
                repository.save(refreshTokenEntity)
        );
    }
}
