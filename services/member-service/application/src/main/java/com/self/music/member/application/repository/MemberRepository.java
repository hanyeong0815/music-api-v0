package com.self.music.member.application.repository;

import com.self.music.member.domain.Member;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findByUsername(String username);
}
