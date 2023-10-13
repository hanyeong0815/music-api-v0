package com.self.music.service;

import com.self.music.domain.Board;
import com.self.music.domain.RefreshTokenRedis;
import com.self.music.domain.Users;
import com.self.music.dto.response.TestResponse;

import java.time.Instant;
import java.util.List;

public interface TestService {
    List<Users> testFindAllUsers();

    List<Board> testFindAllBoard();

    List<RefreshTokenRedis> testFindAllRefreshToken();

//    TestResponse testFindAllBetweenDate(Instant now);

    void testDeleteByToken(String token);
}
