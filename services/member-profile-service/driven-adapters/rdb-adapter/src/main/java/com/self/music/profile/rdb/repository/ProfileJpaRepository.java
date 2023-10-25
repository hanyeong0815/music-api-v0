package com.self.music.profile.rdb.repository;

import com.self.music.profile.rdb.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileJpaRepository extends JpaRepository<ProfileEntity, Long> {
    boolean existsByUsername(String username);

    Optional<ProfileEntity> findByUsername(String username);
}
