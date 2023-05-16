package com.self.music.service;

import com.self.music.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultTestService implements TestService{
    private final UsersRepo usersRepo;
    private final RefreshTokenRedisRepo refreshTokenRedisRepo;
    private final BoardRepo boardRepo;

    @Override
    public List<Users> testFindAllUsers() {
        return usersRepo.findAll();
    }
    @Override
    public List<Board> testFindAllBoard() {
        return boardRepo.findAll();
    }
    @Override
    public List<RefreshTokenRedis> testFindAllRefreshToken() {
        return refreshTokenRedisRepo.findAll();
    }
}
