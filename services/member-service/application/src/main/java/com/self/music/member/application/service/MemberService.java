package com.self.music.member.application.service;

import com.self.music.member.application.exception.MemberErrorCode;
import com.self.music.member.application.repository.MemberRepository;
import com.self.music.member.application.usecase.MemberAuthenticateUseCase;
import com.self.music.member.application.usecase.MemberSaveUseCase;
import com.self.music.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService
        implements
        UserDetailsService,
        MemberAuthenticateUseCase,
        MemberSaveUseCase
{
    private final MemberRepository memberRepository;

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsername(username)
                .map(this::createUserDetails)
                .orElseThrow(MemberErrorCode.NO_SUCH_USER::defaultException);
    }

    private UserDetails createUserDetails(Member member) {
        return User.builder()
                .username(member.username)
                .password(member.password)
                .roles(member.roles.toArray(String[]::new))
                .build();
    }
}
