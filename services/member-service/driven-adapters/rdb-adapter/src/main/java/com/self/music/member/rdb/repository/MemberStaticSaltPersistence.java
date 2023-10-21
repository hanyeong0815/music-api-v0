package com.self.music.member.rdb.repository;

import com.self.music.member.application.repository.MemberStaticSaltRepository;
import com.self.music.member.domain.MemberStaticSalt;
import com.self.music.member.rdb.entity.MemberStaticSaltEntity;
import com.self.music.member.rdb.mapper.MemberStaticSaltEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberStaticSaltPersistence implements MemberStaticSaltRepository {
    private final MemberStaticSaltEntityMapper mapper;
    private final MemberStaticSaltJpaRepository repository;

    @Override
    public MemberStaticSalt save(MemberStaticSalt memberStaticSalt) {
        MemberStaticSaltEntity memberStaticSaltEntity = mapper.toEntity(memberStaticSalt);
        return mapper.toDomain(
                repository.save(memberStaticSaltEntity)
        );
    }

    @Override
    public Optional<MemberStaticSalt> findTopByUsernameOrderByCreatedAt(String username) {
        return repository.findTopByUsernameOrderByCreatedAt(username)
                .map(mapper::toDomain);
    }
}
