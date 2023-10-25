package com.self.music.profile.application.repository;

import com.self.music.profile.domain.Profile;

import java.util.Optional;

public interface ProfileRepository {
    Profile save(Profile profile);
    boolean existsByUsername(String username);

    Optional<Profile> findByUsername(String username);
}
