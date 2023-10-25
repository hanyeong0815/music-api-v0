package com.self.music.member.application.service;

import com.self.music.common.utils.random.StrongStringRandom;
import com.self.music.member.application.aspect.MemberSaveAspect;
import com.self.music.member.application.authentication.token.JwtTokenProvider;
import com.self.music.member.application.exception.MemberErrorCode;
import com.self.music.member.application.mapper.MemberProfileGrpcMapper;
import com.self.music.member.application.repository.MemberRepository;
import com.self.music.member.application.usecase.MemberAuthenticateUseCase;
import com.self.music.member.application.usecase.MemberLoginUseCase;
import com.self.music.member.application.usecase.MemberSaveUseCase;
import com.self.music.member.application.usecase.data.TokenPair;
import com.self.music.member.domain.Member;
import com.self.music.member_profile_grpc.lib.MemberProfileInterfaceGrpc.MemberProfileInterfaceBlockingStub;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
        MemberSaveUseCase,
        MemberLoginUseCase
{
    private final MemberRepository memberRepository;
    private final JwtTokenProvider provider;

    private final PasswordEncoder encoder;
    private final StrongStringRandom random;

    @MemberSaveAspect
    @Override
    public Member save(Member member) {
        String hashPassword = encoder.encode(member.password);
        Member cloneMember = member.toBuilder()
                .password(hashPassword)
                .build();
        return memberRepository.save(cloneMember);
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

    @Override
    public TokenPair login(Authentication authentication) {
        String token = provider.generateToken(authentication);
        String refreshToken = random.nextString();
//        RefreshToken refreshTokenDomain = RefreshToken.builder()
//                .refreshToken(refreshToken)
//                .subject(authentication.getName())
//                .createdAt(Instant.now())
//                .ttl(2628000L)
//                .build();
//        refreshTokenRepository.save(refreshTokenDomain);
        return TokenPair.builder()
                .accessToken(STR."Bearer \{token}")
                .refreshToken(refreshToken)
                .build();
    }
}
