package com.self.music.domain;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshTokenRedisRepo extends CrudRepository<RefreshTokenRedis, String> {
    @Transactional
    @Modifying
    @Query("update RefreshToken r set r.enabled = ?1 where r.subject = ?2")
    void updateEnabledBySubject(boolean b, String subject);

    RefreshToken findByToken(String token);
}
