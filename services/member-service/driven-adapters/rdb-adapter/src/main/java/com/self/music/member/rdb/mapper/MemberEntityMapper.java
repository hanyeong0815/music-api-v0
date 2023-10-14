package com.self.music.member.rdb.mapper;

import com.self.music.common.support.mapper.DomainEntityMapper;
import com.self.music.member.domain.Member;
import com.self.music.member.rdb.entity.MemberEntity;
import org.mapstruct.Mapper;

@Mapper
public interface MemberEntityMapper extends DomainEntityMapper<Member, MemberEntity> {
}
