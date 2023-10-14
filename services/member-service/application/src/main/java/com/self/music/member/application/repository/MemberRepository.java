package com.self.music.member.application.repository;

import com.self.music.member.domain.Member;

public interface MemberRepository {
    Member save(Member member);
}
