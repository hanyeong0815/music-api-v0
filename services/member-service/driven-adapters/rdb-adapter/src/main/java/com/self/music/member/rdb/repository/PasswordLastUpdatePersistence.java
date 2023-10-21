package com.self.music.member.rdb.repository;

import com.self.music.member.application.repository.PasswordLastUpdateRepository;
import com.self.music.member.domain.PasswordLastUpdate;
import com.self.music.member.rdb.entity.PasswordLastUpdateEntity;
import com.self.music.member.rdb.mapper.PasswordLastUpdateEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PasswordLastUpdatePersistence implements PasswordLastUpdateRepository {
    private final PasswordLastUpdateEntityMapper mapper;
    private final PasswordLastUpdateJpaRepository repository;

    @Override
    public PasswordLastUpdate save(PasswordLastUpdate passwordLastUpdate) {
        PasswordLastUpdateEntity passwordLastUpdateEntity = mapper.toEntity(passwordLastUpdate);
        return mapper.toDomain(
                repository.save(passwordLastUpdateEntity)
        );
    }
}
