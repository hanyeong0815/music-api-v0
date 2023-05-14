package com.self.music.domain;

import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.time.Instant;

@Getter
@RedisHash(value = "refresh", timeToLive = 2628000L)
public class RefreshTokenRedis {
    @Id
    private Long id;

    private String token;

    private String subject;

    private Boolean enabled;

    private Instant createdAt;

    private Instant expiredAt;

    private String replacedBy;

    public RefreshTokenRedis(String subject, String token, Instant now, Long expiry){
        this.subject = subject;
        this.token = token;
        enabled = true;
        createdAt = now;
        expiredAt = now.plusSeconds(expiry);
    }
}
