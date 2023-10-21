package com.self.music.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordHistoryLog {
    public UUID id;
    public UUID memberId;
    public String username;
    public String personalSignedDigest;
    public Instant createdAt;
}
