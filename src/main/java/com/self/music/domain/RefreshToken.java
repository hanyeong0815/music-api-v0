package com.self.music.domain;

import lombok.*;

import jakarta.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@Table(name = "refresh_token",
        uniqueConstraints = {
                @UniqueConstraint(name="uk_token", columnNames = "token")
        },
        indexes = {
        @Index(name = "idx_token", columnList = "token"),
        @Index(name = "idx_replaced", columnList = "replaced_by"),
        @Index(name = "idx_subject", columnList = "subject")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "token", length = 128, unique = true)
    private String token;

    @Column(name = "subject", length = 50, nullable = false)
    private String subject;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "expired_at", nullable = false)
    private Instant expiredAt;

    @Column(name = "replaced_by", length = 128)
    private String replacedBy;

    public RefreshToken(String subject, String token, Instant now, Long expiry){
        this.subject = subject;
        this.token = token;
        enabled = true;
        createdAt = now;
        expiredAt = now.plusSeconds(expiry);
    }

    public RefreshToken(String subject, String token, String oldToken, Instant now, Long expiry){
        this(subject, token, now, expiry);
        replacedBy = oldToken;
    }
}
