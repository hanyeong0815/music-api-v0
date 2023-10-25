package com.self.music.member.rdb.entity;

import com.self.music.jpa.UuidBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

import static com.self.music.member.rdb.support.MemberSchemaConstants.SCHEMA;
import static com.self.music.member.rdb.support.MemberSchemaConstants.TB_MEMBER_STATIC_SALT;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        schema = SCHEMA,
        catalog = SCHEMA,
        name = TB_MEMBER_STATIC_SALT
)
public class MemberStaticSaltEntity extends UuidBaseEntity {
    public UUID memberId;
    public String username;
    public String staticSalt;
    public Instant createdAt;
}