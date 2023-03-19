package com.self.music.utills.password;

import com.self.music.properties.AppSecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(AppSecurityProperties.class)
public final class Pbkdf2Encoder extends Pbkdf2PasswordEncoder {
    private final AppSecurityProperties properties;

    Pbkdf2Encoder(AppSecurityProperties properties) {
        super("key-unloaded", 16, 12, SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512);
        this.properties = properties;
    }
}
