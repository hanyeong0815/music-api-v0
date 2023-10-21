package com.self.music.member.rdb.mapper;

import com.self.music.common.support.mapper.DomainEntityMapper;
import com.self.music.member.domain.MemberRegistryDatetime;
import com.self.music.member.rdb.entity.MemberRegistryDatetimeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface MemberRegistryDatetimeEntityMapper extends DomainEntityMapper<MemberRegistryDatetime, MemberRegistryDatetimeEntity> {
}
