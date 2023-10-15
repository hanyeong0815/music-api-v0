package com.self.music.member.rdb.entity;

import com.self.music.jpa.UuidBaseEntity;
import com.self.music.member.domain.type.MemberStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.self.music.member.rdb.support.MemberSchemaConstants.SCHEMA;
import static com.self.music.member.rdb.support.MemberSchemaConstants.TB_MEMBER;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        schema = SCHEMA,
        catalog = SCHEMA,
        name = TB_MEMBER
)
public class MemberEntity extends UuidBaseEntity implements UserDetails {
    public String username;
    public String password;
    @Enumerated(EnumType.STRING)
    public MemberStatus status;
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    public List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
