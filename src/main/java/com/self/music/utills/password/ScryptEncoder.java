package com.self.music.utills.password;

import com.self.music.properties.AppSecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(AppSecurityProperties.class)
public final class ScryptEncoder extends SCryptPasswordEncoder {
    private final AppSecurityProperties properties;

    private static final int DEFAULT_CPU_COST = 65536;

    private static final int DEFAULT_MEMORY_COST = 8;

    private static final int DEFAULT_PARALLELISM = 1;

    private static final int DEFAULT_KEY_LENGTH = 32;

    private static final int DEFAULT_SALT_LENGTH = 16;

    ScryptEncoder(AppSecurityProperties properties) {
        super(DEFAULT_CPU_COST, DEFAULT_MEMORY_COST, DEFAULT_PARALLELISM, DEFAULT_KEY_LENGTH, DEFAULT_SALT_LENGTH);
        this.properties = properties;
    }
}
