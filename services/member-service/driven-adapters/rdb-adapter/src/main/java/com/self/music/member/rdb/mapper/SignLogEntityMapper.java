package com.self.music.member.rdb.mapper;

import com.self.music.common.support.mapper.DomainEntityMapper;
import com.self.music.member.domain.type.SignLog;
import com.self.music.member.rdb.entity.SignLogEntity;
import org.mapstruct.Mapper;

@Mapper
public interface SignLogEntityMapper extends DomainEntityMapper<SignLog, SignLogEntity> {
}
