package com.self.music.member.rdb.entity;

import com.self.music.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.self.music.member.rdb.support.MemberSchemaConstants.SCHEMA;
import static com.self.music.member.rdb.support.MemberSchemaConstants.TB_MEMBER_ROLES;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        schema = SCHEMA,
        catalog = SCHEMA,
        name = TB_MEMBER_ROLES
)
public class MemberRolesEntity extends BaseEntity {
    public UUID memberId;
    public String roles;
}
