package com.self.music.member.rdb.repository;

import com.self.music.member.rdb.entity.SignLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignLogJpaRepository extends JpaRepository<SignLogEntity, Long> {
}
