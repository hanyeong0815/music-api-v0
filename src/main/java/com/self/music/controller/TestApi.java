package com.self.music.controller;

import com.self.music.domain.Users;
import com.self.music.service.DefaultUserService;
import com.self.music.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestApi {
    private final UserService userService;

    @GetMapping("user-all")
    public List<Users> findAll() {
        return userService.testFindAllUsers();
    }

}
