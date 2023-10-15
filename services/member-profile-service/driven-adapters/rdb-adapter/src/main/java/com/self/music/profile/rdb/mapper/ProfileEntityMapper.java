package com.self.music.profile.rdb.mapper;

import com.self.music.common.support.mapper.DomainEntityMapper;
import com.self.music.profile.domain.Profile;
import com.self.music.profile.rdb.entity.ProfileEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ProfileEntityMapper extends DomainEntityMapper<Profile, ProfileEntity> {
}
