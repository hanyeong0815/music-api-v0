package com.self.music.profile.application.repository;

import com.self.music.profile.domain.Profile;

public interface ProfileRepository {
    Profile save(Profile profile);
}
