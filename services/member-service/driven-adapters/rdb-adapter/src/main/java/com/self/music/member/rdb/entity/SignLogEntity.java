package com.self.music.member.rdb.entity;

import com.self.music.jpa.BaseEntity;
import com.self.music.member.domain.type.SignType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

import static com.self.music.member.rdb.support.MemberSchemaConstants.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        schema = SCHEMA,
        catalog = SCHEMA,
        name = TB_SIGN_LOG
)
public class SignLogEntity extends BaseEntity {
    public UUID memberId;
    public String username;
    @Enumerated(EnumType.STRING)
    public SignType eventType;
    public String remarks;
    public Instant createdAt;
}
