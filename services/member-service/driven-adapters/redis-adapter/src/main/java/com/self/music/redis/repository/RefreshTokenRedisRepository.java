package com.self.music.redis.repository;

import com.self.music.redis.entity.RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshTokenEntity, String> {
}
