package com.self.music.member_profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import static com.self.music.common.support.ServerConstants.BASE_PACKAGE;

@SpringBootApplication(scanBasePackages = BASE_PACKAGE)
@ConfigurationPropertiesScan(basePackages = BASE_PACKAGE)
public class MemberProfileApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberProfileApplication.class, args);
    }
}