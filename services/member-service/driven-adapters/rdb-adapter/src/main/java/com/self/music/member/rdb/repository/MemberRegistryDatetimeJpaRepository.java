package com.self.music.member.rdb.repository;

import com.self.music.member.rdb.entity.MemberRegistryDatetimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRegistryDatetimeJpaRepository extends JpaRepository<MemberRegistryDatetimeEntity, Long> {
}
