package com.self.music.member.rdb.mapper;

import com.self.music.common.support.mapper.DomainEntityMapper;
import com.self.music.member.domain.MemberStaticSalt;
import com.self.music.member.rdb.entity.MemberStaticSaltEntity;
import org.mapstruct.Mapper;

@Mapper
public interface MemberStaticSaltEntityMapper extends DomainEntityMapper<MemberStaticSalt, MemberStaticSaltEntity> {
}
