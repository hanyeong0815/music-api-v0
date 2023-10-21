package com.self.music.member.application.repository;

import com.self.music.member.domain.PasswordHistoryLog;

public interface PasswordHistoryLogRepository {
    PasswordHistoryLog save(PasswordHistoryLog passwordHistoryLog);
}
