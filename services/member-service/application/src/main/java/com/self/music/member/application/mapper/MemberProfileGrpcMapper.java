package com.self.music.member.application.mapper;

import com.self.music.member.application.usecase.data.MemberProfileDto;
import com.self.music.member_profile_grpc.lib.MemberProfileDetailResponse;
import com.self.music.member_profile_grpc.lib.MemberProfileSaveRequest;
import org.mapstruct.Mapper;

@Mapper
public interface MemberProfileGrpcMapper {
    MemberProfileSaveRequest from(MemberProfileDto memberProfileDto);
    MemberProfileDto from(MemberProfileDetailResponse memberProfileDto);
}
