package com.self.music.member.rdb.repository;

import com.self.music.member.domain.Member;
import com.self.music.member.rdb.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, UUID> {
    Optional<MemberEntity> findByUsername(String username);
}
