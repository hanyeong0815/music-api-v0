package com.self.music.properties;

import com.self.music.properties.bcrypt.AppSecurityPasswordBcryptProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

public record AppSecurityPasswordProperties(
        String encoder,
        @NestedConfigurationProperty AppSecurityPasswordBcryptProperties bcrypt
) {
}
