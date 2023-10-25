package com.self.music.member_profile.internal.service;

import com.self.music.member_profile.mapper.ProfileMapper;
import com.self.music.member_profile_grpc.lib.MemberProfileDetailResponse;
import com.self.music.member_profile_grpc.lib.MemberProfileInterfaceGrpc.MemberProfileInterfaceImplBase;
import com.self.music.member_profile_grpc.lib.MemberProfileSaveRequest;
import com.self.music.profile.application.usecase.ProfileSaveUseCase;
import com.self.music.profile.domain.Profile;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class MemberProfileSaveProxyService extends MemberProfileInterfaceImplBase {
    private final ProfileSaveUseCase profileSaveUseCase;
    private final ProfileMapper mapper;

    @Override
    public void save(MemberProfileSaveRequest request, StreamObserver<MemberProfileDetailResponse> responseObserver) {
        Profile profile = mapper.from(request);
        Profile savedProfile = profileSaveUseCase.save(profile);
        MemberProfileDetailResponse response = mapper.from(savedProfile);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
