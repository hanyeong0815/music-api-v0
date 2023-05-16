package com.self.music.domain;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRedisRepo extends CrudRepository<RefreshTokenRedis, String> {
    @Transactional
    @Modifying
    @Query("update RefreshToken r set r.enabled = ?1 where r.subject = ?2")
    void updateEnabledBySubject(boolean b, String subject);

    Optional<RefreshTokenRedis> findByToken(String token);

    List<RefreshTokenRedis> findAll();
}
