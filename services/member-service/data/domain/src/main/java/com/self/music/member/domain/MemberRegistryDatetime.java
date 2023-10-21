package com.self.music.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegistryDatetime {
    public Long id;
    public UUID memberId;
    public Instant createdAt;
}
