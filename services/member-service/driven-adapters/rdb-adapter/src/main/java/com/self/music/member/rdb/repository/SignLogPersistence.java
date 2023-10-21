package com.self.music.member.rdb.repository;

import com.self.music.member.application.repository.SignLogRepository;
import com.self.music.member.domain.type.SignLog;
import com.self.music.member.rdb.entity.SignLogEntity;
import com.self.music.member.rdb.mapper.SignLogEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SignLogPersistence implements SignLogRepository {
    private final SignLogEntityMapper mapper;
    private final SignLogJpaRepository repository;


    @Override
    public SignLog save(SignLog signLog) {
        SignLogEntity signLogEntity = mapper.toEntity(signLog);
        return mapper.toDomain(
                repository.save(signLogEntity)
        );
    }
}
