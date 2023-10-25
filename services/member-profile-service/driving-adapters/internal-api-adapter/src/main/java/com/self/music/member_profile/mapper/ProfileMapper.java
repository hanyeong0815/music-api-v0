package com.self.music.member_profile.mapper;

import com.self.music.member_profile_grpc.lib.MemberProfileDetailResponse;
import com.self.music.member_profile_grpc.lib.MemberProfileSaveRequest;
import com.self.music.profile.domain.Profile;
import com.self.music.profile.domain.type.GenderType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;

@Mapper
public interface ProfileMapper {
    Profile from(MemberProfileSaveRequest memberProfileSaveRequest);
    MemberProfileDetailResponse from(Profile profile);

    @ValueMapping(target = MappingConstants.NULL, source = MappingConstants.ANY_REMAINING)
    GenderType toNativeEnum(com.self.music.member_profile_grpc.lib.GenderType genderType);
    @ValueMapping(target = MappingConstants.NULL, source = MappingConstants.ANY_REMAINING)
    com.self.music.member_profile_grpc.lib.GenderType toRequestEnum(GenderType genderType);
}
