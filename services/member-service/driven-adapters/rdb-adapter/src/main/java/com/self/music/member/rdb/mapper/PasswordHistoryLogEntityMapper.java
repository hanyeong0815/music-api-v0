package com.self.music.member.rdb.mapper;

import com.self.music.common.support.mapper.DomainEntityMapper;
import com.self.music.member.domain.PasswordHistoryLog;
import com.self.music.member.rdb.entity.PasswordHistoryLogEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PasswordHistoryLogEntityMapper extends DomainEntityMapper<PasswordHistoryLog, PasswordHistoryLogEntity> {
}
