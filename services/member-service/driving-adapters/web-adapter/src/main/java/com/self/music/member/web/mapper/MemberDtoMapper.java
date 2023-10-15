package com.self.music.member.web.mapper;

import com.self.music.member.domain.Member;
import com.self.music.member.web.dto.MemberSaveDto.MemberSaveRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface MemberDtoMapper {
    Member from(MemberSaveRequestDto memberSaveRequestDto);
}
