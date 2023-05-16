package com.self.music.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Date;

@Getter
@RedisHash(value = "refresh", timeToLive = 2628000L)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRedis {
    @Id
    private Long id;

    @Indexed
    private String token;

    private String subject;

    private Boolean enabled;

    private Date createdAt;

    private Date expiredAt;

    private String replacedBy;

    public RefreshTokenRedis(Long id, String token, String subject, Boolean enabled, Date createdAt, Date expiredAt) {
        this.id = id;
        this.token = token;
        this.subject = subject;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
    }
}
