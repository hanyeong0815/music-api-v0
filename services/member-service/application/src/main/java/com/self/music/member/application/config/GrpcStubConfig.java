package com.self.music.member.application.config;

import com.self.music.member_profile_grpc.lib.MemberProfileInterfaceGrpc;
import com.self.music.member_profile_grpc.lib.MemberProfileInterfaceGrpc.MemberProfileInterfaceBlockingStub;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcStubConfig {
    @Bean
    public MemberProfileInterfaceBlockingStub memberProfileInterfaceBlockingStub() {
        return MemberProfileInterfaceGrpc.newBlockingStub(
                ManagedChannelBuilder
                        .forAddress("localhost", 8091)
                        .usePlaintext()
                        .build()
        );
    }
}
