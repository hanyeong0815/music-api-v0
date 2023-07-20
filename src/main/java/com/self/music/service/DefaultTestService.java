package com.self.music.service;

import com.self.music.domain.*;
import com.self.music.dto.response.Board1;
import com.self.music.dto.response.TestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public TestResponse testFindAllBetweenDate(Instant now) {
        Instant thr = now.minus(Duration.ofDays(90));
        Instant six = now.minus(Duration.ofDays(180));
        System.out.println(thr);
        System.out.println(six);
        List<Board> boards = boardRepo.findByUploadDateBetween(thr);
        List<Board> boards1 = boardRepo.findByUploadDateBetween(six);
        return TestResponse.builder().boards1(boards.stream().map((board)->{
            Board1 board1 = Board1.builder().id(board.getId()).uploadDate(board.getUploadDate()).userId(board.getUserId()).build();
            return board1;
        }).collect(Collectors.toList())).boards2(boards1.stream().map((board)->{
            Board1 board1 = Board1.builder().id(board.getId()).uploadDate(board.getUploadDate()).userId(board.getUserId()).build();
            return board1;
        }).collect(Collectors.toList())).build();
    }
}
