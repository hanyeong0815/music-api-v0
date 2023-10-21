package com.self.music.member.application.repository;

import com.self.music.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);
}
