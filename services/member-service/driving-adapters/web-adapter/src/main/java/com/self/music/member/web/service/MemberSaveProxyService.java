package com.self.music.member.web.service;

import com.self.music.member.application.usecase.MemberSaveUseCase;
import com.self.music.member.domain.Member;
import com.self.music.member.domain.type.MemberStatus;
import com.self.music.member.web.dto.MemberSaveDto.MemberSaveRequestDto;
import com.self.music.member.web.dto.MemberSaveDto.MemberSaveResponseDto;
import com.self.music.member.web.mapper.MemberDtoMapper;
import com.self.music.member_profile_grpc.lib.MemberProfileDetailResponse;
import com.self.music.member_profile_grpc.lib.MemberProfileInterfaceGrpc.MemberProfileInterfaceBlockingStub;
import com.self.music.member_profile_grpc.lib.MemberProfileSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSaveProxyService {
    private final MemberSaveUseCase memberSaveUseCase;
    private final MemberProfileInterfaceBlockingStub memberProfileInterfaceBlockingStub;
    private final MemberDtoMapper memberDtoMapper;

    public MemberSaveResponseDto memberSave(MemberSaveRequestDto dto) {
        Member member = memberDtoMapper.from(dto, MemberStatus.ACTIVE);
        Member savedMember = memberSaveUseCase.save(member);

        MemberProfileSaveRequest memberProfileSaveRequest = memberDtoMapper.from(savedMember, dto);
        MemberProfileDetailResponse response = memberProfileInterfaceBlockingStub.save(memberProfileSaveRequest);

        return memberDtoMapper.from(response, savedMember.roles);
    }
}
