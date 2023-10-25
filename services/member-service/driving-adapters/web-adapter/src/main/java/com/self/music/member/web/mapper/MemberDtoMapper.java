package com.self.music.member.web.mapper;

import com.self.music.member.domain.Member;
import com.self.music.member.domain.type.GenderType;
import com.self.music.member.domain.type.MemberStatus;
import com.self.music.member.web.dto.MemberSaveDto.MemberSaveRequestDto;
import com.self.music.member.web.dto.MemberSaveDto.MemberSaveResponseDto;
import com.self.music.member_profile_grpc.lib.MemberProfileDetailResponse;
import com.self.music.member_profile_grpc.lib.MemberProfileSaveRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface MemberDtoMapper {
    Member from(MemberSaveRequestDto memberSaveRequestDto, MemberStatus status);

    @Mappings({
            @Mapping(target = "memberId", source = "member.id"),
            @Mapping(target = "username", source = "member.username")
    })
    MemberProfileSaveRequest from(Member member, MemberSaveRequestDto dto);

    MemberSaveResponseDto from(MemberProfileDetailResponse response, List<String> roles);
    @ValueMapping(target = MappingConstants.NULL, source = MappingConstants.ANY_REMAINING)
    GenderType toNativeEnum(com.self.music.member_profile_grpc.lib.GenderType genderType);

    @ValueMapping(target = MappingConstants.NULL, source = MappingConstants.ANY_REMAINING)
    com.self.music.member_profile_grpc.lib.GenderType toRequestEnum(GenderType genderType);
}
