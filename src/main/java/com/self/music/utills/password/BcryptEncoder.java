package com.self.music.utills.password;

import com.self.music.properties.AppSecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(AppSecurityProperties.class)
public final class BcryptEncoder extends BCryptPasswordEncoder {
    private final AppSecurityProperties properties;

    public BcryptEncoder(AppSecurityProperties properties) {
        super(
                properties.password() != null &&
                properties.password().bcrypt() != null &&
                properties.password().bcrypt().costFactor() != null
                        ? properties.password().bcrypt().costFactor()
                        : 10
        );
        this.properties = properties;
    }
}