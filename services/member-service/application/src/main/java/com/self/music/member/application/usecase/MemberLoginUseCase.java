package com.self.music.member.application.usecase;

import com.self.music.member.application.usecase.data.TokenPair;
import org.springframework.security.core.Authentication;

public interface MemberLoginUseCase {
    TokenPair login(Authentication authentication);
}
