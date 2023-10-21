package com.self.music.member.rdb.repository;

import com.self.music.member.rdb.entity.MemberStaticSaltEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberStaticSaltJpaRepository extends JpaRepository<MemberStaticSaltEntity, UUID> {
    Optional<MemberStaticSaltEntity> findTopByUsernameOrderByCreatedAt(String username);
}
