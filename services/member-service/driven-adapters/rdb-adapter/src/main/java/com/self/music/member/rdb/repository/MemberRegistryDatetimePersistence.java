package com.self.music.member.rdb.repository;

import com.self.music.member.application.repository.MemberRegistryDatetimeRepository;
import com.self.music.member.domain.MemberRegistryDatetime;
import com.self.music.member.rdb.entity.MemberRegistryDatetimeEntity;
import com.self.music.member.rdb.mapper.MemberRegistryDatetimeEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRegistryDatetimePersistence implements MemberRegistryDatetimeRepository {
    private final MemberRegistryDatetimeEntityMapper mapper;
    private final MemberRegistryDatetimeJpaRepository repository;

    @Override
    public MemberRegistryDatetime save(MemberRegistryDatetime memberRegistryDatetime) {
        MemberRegistryDatetimeEntity memberRegistryDatetimeEntity = mapper
                .toEntity(memberRegistryDatetime);
        return mapper.toDomain(
                repository.save(memberRegistryDatetimeEntity)
        );
    }
}
