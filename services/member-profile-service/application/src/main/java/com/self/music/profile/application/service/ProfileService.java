package com.self.music.profile.application.service;

import com.self.music.profile.application.exception.ProfileErrorCode;
import com.self.music.profile.application.repository.ProfileRepository;
import com.self.music.profile.application.usecase.ProfileDetailViewUseCase;
import com.self.music.profile.application.usecase.ProfileSaveUseCase;
import com.self.music.profile.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.self.music.common.utils.exception.Preconditions.validate;

@Service
@RequiredArgsConstructor
public class ProfileService implements ProfileSaveUseCase, ProfileDetailViewUseCase {
    private final ProfileRepository profileRepository;

    @Override
    public Profile save(Profile profile) {
        boolean hasProfile = profileRepository.existsByUsername(profile.username);
        validate(
                hasProfile,
                ProfileErrorCode.ALREADY_USED
        );
        return profileRepository.save(profile);
    }

    @Override
    public Profile detailView(String username) {
        return profileRepository.findByUsername(username)
                .orElseThrow(
                        ProfileErrorCode.NO_SUCH_USER::defaultException
                );
    }
}
