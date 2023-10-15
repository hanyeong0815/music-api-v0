package com.self.music.profile.rdb.repository;

import com.self.music.profile.application.repository.ProfileRepository;
import com.self.music.profile.domain.Profile;
import com.self.music.profile.rdb.entity.ProfileEntity;
import com.self.music.profile.rdb.mapper.ProfileEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProfilePersistence implements ProfileRepository {
    private final ProfileEntityMapper mapper;
    private final ProfileJpaRepository repository;

    @Override
    public Profile save(Profile profile) {
        ProfileEntity profileEntity = mapper.toEntity(profile);
        return mapper.toDomain(
                repository.save(profileEntity)
        );
    }
}