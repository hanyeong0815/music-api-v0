package com.self.music.profile.domain;

import com.self.music.profile.domain.type.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    public Long id;
    public UUID memberId;
    public String username;
    public String email;
    public GenderType genderType;
    public String name;
    public String nickname;
}
