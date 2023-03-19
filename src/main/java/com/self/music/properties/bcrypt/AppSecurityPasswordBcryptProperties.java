package com.self.music.properties.bcrypt;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@ConfigurationPropertiesBinding
public record AppSecurityPasswordBcryptProperties(Integer costFactor) {
    public AppSecurityPasswordBcryptProperties() {
        this(10);
    }
}
