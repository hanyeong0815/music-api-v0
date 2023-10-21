package com.self.music.member.rdb.repository;

import com.self.music.member.application.repository.PasswordHistoryLogRepository;
import com.self.music.member.domain.PasswordHistoryLog;
import com.self.music.member.rdb.entity.PasswordHistoryLogEntity;
import com.self.music.member.rdb.mapper.PasswordHistoryLogEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PasswordHistoryLogPersistence implements PasswordHistoryLogRepository {
    private final PasswordHistoryLogEntityMapper mapper;
    private final PasswordHistoryLogJpaRepository repository;

    @Override
    public PasswordHistoryLog save(PasswordHistoryLog passwordHistoryLog) {
        PasswordHistoryLogEntity passwordHistoryLogEntity = mapper.toEntity(passwordHistoryLog);
        return mapper.toDomain(
                repository.save(passwordHistoryLogEntity)
        );
    }
}
