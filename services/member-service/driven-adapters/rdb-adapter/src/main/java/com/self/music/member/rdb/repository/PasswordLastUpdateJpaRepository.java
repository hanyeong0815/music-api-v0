package com.self.music.member.rdb.repository;

import com.self.music.member.rdb.entity.PasswordLastUpdateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordLastUpdateJpaRepository extends JpaRepository<PasswordLastUpdateEntity, Long> {
}
