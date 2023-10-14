package com.self.music.member.rdb.repository;

import com.self.music.member.application.repository.MemberRepository;
import com.self.music.member.domain.Member;
import com.self.music.member.rdb.entity.MemberEntity;
import com.self.music.member.rdb.mapper.MemberEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberPersistence implements MemberRepository {
    private final MemberEntityMapper mapper;
    private final MemberJpaRepository repository;

    @Override
    public Member save(Member member) {
        MemberEntity memberEntity = mapper.toEntity(member);
        return mapper.toDomain(
                repository.save(memberEntity)
        );
    }
}
