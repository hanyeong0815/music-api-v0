package com.self.music.member.web.service;

import com.self.music.member.application.usecase.MemberSaveUseCase;
import com.self.music.member.domain.Member;
import com.self.music.member.domain.type.MemberStatus;
import com.self.music.member.web.dto.MemberSaveDto.MemberSaveRequestDto;
import com.self.music.member.web.dto.MemberSaveDto.MemberSaveResponseDto;
import com.self.music.member.web.mapper.MemberDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSaveProxyService {
    private final MemberSaveUseCase memberSaveUseCase;
    private final MemberDtoMapper memberDtoMapper;

    public MemberSaveResponseDto memberSave(MemberSaveRequestDto dto) {
        Member member = memberDtoMapper.from(dto, MemberStatus.ACTIVE);
        Member savedMember = memberSaveUseCase.save(member);
        return MemberSaveResponseDto.builder()
                .username(savedMember.username)
                .roles(savedMember.roles)
                .build();
    }
}
