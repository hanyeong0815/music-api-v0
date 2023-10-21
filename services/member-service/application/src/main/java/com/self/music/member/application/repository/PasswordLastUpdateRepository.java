package com.self.music.member.application.repository;

import com.self.music.member.domain.PasswordLastUpdate;

public interface PasswordLastUpdateRepository {
    PasswordLastUpdate save(PasswordLastUpdate passwordLastUpdate);
}
