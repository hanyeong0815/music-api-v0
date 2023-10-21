package com.self.music.member.application.aspect;

import com.self.music.common.utils.random.StrongStringRandom;
import com.self.music.member.application.exception.MemberErrorCode;
import com.self.music.member.application.repository.*;
import com.self.music.member.domain.*;
import com.self.music.member.domain.type.SignLog;
import com.self.music.member.domain.type.SignType;
import com.self.music.passwordEncoder.encoder.Sha256SaltedEncoderSupplier;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.self.music.common.utils.exception.Preconditions.validate;

@Component
@Aspect
@RequiredArgsConstructor
public class SaveAspect {
    private final MemberRepository memberRepository;
    private final SignLogRepository signLogRepository;
    private final PasswordHistoryLogRepository passwordHistoryLogRepository;
    private final MemberRegistryDatetimeRepository memberRegistryDatetimeRepository;
    private final PasswordLastUpdateRepository passwordLastUpdateRepository;
    private final MemberStaticSaltRepository memberStaticSaltRepository;

    private final Sha256SaltedEncoderSupplier sha256SaltedEncoderSupplier;
    private final StrongStringRandom strongStringRandom;

    @Before(value = "@annotation(MemberSaveAspect) && args(member)")
    public void hasMember(Member member) {
        boolean hasUsername = memberRepository.existsByUsername(member.username);
        validate(
                hasUsername,
                MemberErrorCode.USERNAME_ALREADY_USED
        );
    }

    @AfterReturning(value = "@annotation(MemberSaveAspect)", returning = "member")
    public SignLog saveSignLog(Member member) {
        SignLog signLog = SignLog.builder()
                .memberId(member.id)
                .username(member.username)
                .eventType(SignType.SIGN_UP)
                .remarks("회원가입")
                .createdAt(Instant.now())
                .build();
        return signLogRepository.save(signLog);
    }

    @AfterReturning(value = "@annotation(MemberSaveAspect)", returning = "member")
    public MemberRegistryDatetime saveMemberRegistryDatetime(Member member) {
        MemberRegistryDatetime memberRegistryDatetime = MemberRegistryDatetime.builder()
                .memberId(member.id)
                .createdAt(Instant.now())
                .build();
        return memberRegistryDatetimeRepository.save(memberRegistryDatetime);
    }

    @AfterReturning(value = "@annotation(MemberSaveAspect)", returning = "member")
    public PasswordLastUpdate savePasswordLastUpdate(Member member) {
        PasswordLastUpdate passwordHistoryLog = PasswordLastUpdate.builder()
                .memberId(member.id)
                .username(member.username)
                .createdAt(Instant.now())
                .build();
        return passwordLastUpdateRepository.save(passwordHistoryLog);
    }

    @AfterReturning(
            value = "@annotation(MemberSaveAspect) && args(member)",
            returning = "savedMember",
            argNames = "member,savedMember"
    )
    public PasswordHistoryLog savePasswordHistoryLog(Member member, Member savedMember) {
        MemberStaticSalt memberStaticSalt = memberStaticSaltRepository
                .findTopByUsernameOrderByCreatedAt(member.username)
                .orElseGet(() -> {
                    String salt = strongStringRandom.nextString();
                    MemberStaticSalt newMemberStaticSalt = MemberStaticSalt.builder()
                            .memberId(savedMember.id)
                            .username(savedMember.username)
                            .staticSalt(salt)
                            .createdAt(Instant.now())
                            .build();

                    return memberStaticSaltRepository.save(newMemberStaticSalt);
                });

        String staticSalt = memberStaticSalt.staticSalt;
        String rawPassword = member.password;
        String sha256Password = sha256SaltedEncoderSupplier
                .getEncoder(staticSalt)
                .encode(rawPassword);

        PasswordHistoryLog passwordHistoryLog = PasswordHistoryLog.builder()
                .memberId(savedMember.id)
                .username(savedMember.username)
                .personalSignedDigest(sha256Password)
                .createdAt(Instant.now())
                .build();

        return passwordHistoryLogRepository.save(passwordHistoryLog);
    }
}
