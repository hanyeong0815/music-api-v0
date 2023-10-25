package com.self.music.profile.application.usecase;

import com.self.music.profile.domain.Profile;

public interface ProfileDetailViewUseCase {
    Profile detailView(String username);
}
