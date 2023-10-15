package com.self.music.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRoles {
    public Long id;
    public UUID memberId;
    public String roles;
}
