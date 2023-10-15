package com.self.music.member.application.usecase;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface MemberAuthenticateUseCase {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
