package com.self.music.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
    @Transactional
    @Modifying
    @Query("update RefreshToken r set r.enabled = ?1 where r.subject = ?2")
    void updateEnabledBySubject(boolean b, String subject);
}
