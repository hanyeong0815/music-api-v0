package com.self.music.member_profile.internal.server;

import com.self.music.member_profile.internal.service.MemberProfileSaveProxyService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberProfileServerRunner implements ApplicationRunner {
    private static final int PORT = 8091;
    private final MemberProfileSaveProxyService memberProfileSaveProxyService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Server server = ServerBuilder
                .forPort(PORT)
                .addService(memberProfileSaveProxyService)
                .build();
        server.start();
        server.awaitTermination();
    }
}
