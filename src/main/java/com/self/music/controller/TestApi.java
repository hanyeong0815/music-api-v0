package com.self.music.controller;

import com.self.music.domain.Board;
import com.self.music.domain.RefreshTokenRedis;
import com.self.music.domain.Users;
import com.self.music.dto.response.TestResponse;
import com.self.music.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestApi {
    private final TestService testService;

    @GetMapping("user-all")
    public List<Users> findAll() {
        return testService.testFindAllUsers();
    }

    @GetMapping("board-all")
    public List<Board> findAllBoard() {return testService.testFindAllBoard();}

    @GetMapping("refresh-all")
    public List<RefreshTokenRedis> findAllRefresh() {return testService.testFindAllRefreshToken();}

    @GetMapping("/test1")
    public TestResponse findBetweenDate() {
        Instant now = Instant.now();
        return testService.testFindAllBetweenDate(now);
    }
}
