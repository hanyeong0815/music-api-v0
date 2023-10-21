package com.self.music.member.application.repository;

import com.self.music.member.domain.MemberStaticSalt;

import java.util.Optional;

public interface MemberStaticSaltRepository {
    MemberStaticSalt save(MemberStaticSalt memberStaticSalt);
    Optional<MemberStaticSalt> findTopByUsernameOrderByCreatedAt(String username);
}
