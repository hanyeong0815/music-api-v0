package com.self.music.profile.rdb.repository;

import com.self.music.profile.rdb.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileJpaRepository extends JpaRepository<ProfileEntity, Long> {
}
