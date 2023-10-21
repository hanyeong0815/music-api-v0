package com.self.music.member.application.repository;

import com.self.music.member.domain.type.SignLog;

public interface SignLogRepository {
    SignLog save(SignLog signLog);
}
