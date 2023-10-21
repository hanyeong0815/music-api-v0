package com.self.music.member.rdb.mapper;

import com.self.music.common.support.mapper.DomainEntityMapper;
import com.self.music.member.domain.PasswordLastUpdate;
import com.self.music.member.rdb.entity.PasswordLastUpdateEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PasswordLastUpdateEntityMapper extends DomainEntityMapper<PasswordLastUpdate, PasswordLastUpdateEntity> {
}
