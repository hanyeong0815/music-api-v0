package com.self.music.redis.mapper;

import com.self.music.common.support.mapper.DomainEntityMapper;
import com.self.music.member.domain.RefreshToken;
import com.self.music.redis.entity.RefreshTokenEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RefreshTokenEntityMapper extends DomainEntityMapper<RefreshToken, RefreshTokenEntity> {
}
