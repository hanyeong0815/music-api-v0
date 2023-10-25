package com.self.music.web.service;

import com.self.music.profile.application.usecase.ProfileDetailViewUseCase;
import com.self.music.profile.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileDetailViewProxyService {
    private final ProfileDetailViewUseCase profileDetailViewUseCase;

    public Profile findProfileDetailView(String username) {
        return profileDetailViewUseCase.detailView(username);
    }
}
