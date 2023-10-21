package com.self.music.member.rdb.repository;

import com.self.music.member.rdb.entity.PasswordHistoryLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PasswordHistoryLogJpaRepository extends JpaRepository<PasswordHistoryLogEntity, UUID> {
}
