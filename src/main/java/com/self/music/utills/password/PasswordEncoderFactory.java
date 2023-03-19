package com.self.music.utills.password;

import com.self.music.properties.AppSecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(AppSecurityProperties.class)
public class PasswordEncoderFactory {
    private final BcryptEncoder bcryptEncoder;
    private final Pbkdf2Encoder pbkdf2Encoder;
    private final ScryptEncoder scryptEncoder;
    private final AppSecurityProperties appSecurityProperties;

    public enum EncoderType {
        BCRYPT,
        PBKDF2,
        SCRYPT
    }

    public PasswordEncoder defaultEncoder() {
        EncoderType encoderType = EncoderType.valueOf(appSecurityProperties.password().encoder().toUpperCase());
        return create(encoderType);
    }

    public PasswordEncoder create (EncoderType encoderType) {
        return switch (encoderType) {
            case BCRYPT -> bcryptEncoder;
            case PBKDF2 -> pbkdf2Encoder;
            case SCRYPT -> scryptEncoder;
        };
    }
}
