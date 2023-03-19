package com.self.music.properties;

import com.self.music.config.CommonPropertiesScanConfig.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix = "app.security")
@ConfigurationPropertiesScan
@ConfigurationPropertiesBinding
@PropertySource(value = "classpath:/security/security-app-config.yml",
        factory = YamlPropertySourceFactory.class)
public record AppSecurityProperties(
        @NestedConfigurationProperty AppSecurityPasswordProperties password
) {
}
