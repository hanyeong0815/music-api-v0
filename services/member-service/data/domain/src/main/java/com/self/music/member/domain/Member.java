package com.self.music.member.domain;

import com.self.music.member.domain.type.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    public UUID id;
    public String username;
    public String password;
    public MemberStatus status;
    public List<String> roles = new ArrayList<>();
}
