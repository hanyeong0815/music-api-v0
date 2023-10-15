package com.self.music.profile.rdb.entity;

import com.self.music.jpa.BaseEntity;
import com.self.music.profile.domain.type.GenderType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.self.music.profile.rdb.support.ProfileSchemaConstants.SCHEMA;
import static com.self.music.profile.rdb.support.ProfileSchemaConstants.TB_PROFILE;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        schema = SCHEMA,
        catalog = SCHEMA,
        name = TB_PROFILE
)
public class ProfileEntity extends BaseEntity {
    public UUID memberId;
    public String username;
    public String email;
    public GenderType genderType;
    public String name;
    public String nickname;
}
