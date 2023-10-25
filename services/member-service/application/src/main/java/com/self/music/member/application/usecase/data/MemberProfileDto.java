package com.self.music.member.application.usecase.data;

import com.self.music.member_profile_grpc.lib.GenderType;

public record MemberProfileDto(
        String memberId,
        String username,
        String email,
        GenderType genderType,
        String name,
        String nickname
) {
}
