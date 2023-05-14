package com.self.music.controller;

import com.self.music.domain.Users;
import com.self.music.dto.request.ChangePwDto.ChangePwRequest;
import com.self.music.dto.request.ChangePwDto.HasPwRequest;
import com.self.music.dto.request.CheckPwDto.CheckPwRequest;
import com.self.music.dto.request.LoginDto.LoginRequest;
import com.self.music.dto.request.SignUpRequest.SignUpReq;
import com.self.music.dto.response.JwtResponse;
import com.self.music.dto.response.UsersResponse.UsersRes;
import com.self.music.service.AuthenticationService;
import com.self.music.service.UserService;
import com.self.music.utills.password.PasswordEncoderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final PasswordEncoderFactory encoder;

    @PostMapping("/signup")
    public ResponseEntity<JwtResponse> signUp(@RequestBody SignUpReq req) {
        Users users = Users.builder()
                .username(req.getUserName())
                .password(encoder.defaultEncoder().encode(req.getPassword()))
                .email(req.getEmail())
                .name(req.getName())
                .nickname(req.getNickname())
                .genderType(req.getGender())
                .roles(req.getRoles())
                .build();
        Authentication newUsers = userService.signUp(users);
        if (newUsers == null) {
            return null;
        }

        return this.login(newUsers);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(Authentication authentication) {
        JwtResponse jwtResponse = userService.login(authentication);
        return ResponseEntity.ok(jwtResponse);
    }

    @GetMapping("/home")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public UsersRes usersInfo(String username) {

        return userService.usersInfo(username);
    }

    @GetMapping("/delete-user")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteUser(Long userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/find-id")
    public String findIdFiltering(String name, String email) {
        return userService.findIdFiltering(name, email);
    }

    @GetMapping("/find-pw")
    public boolean findPwByUserInfo(@RequestBody HasPwRequest req) {
        return userService.hasUserInfoByUserInfo(req);
    }

    @PostMapping("/change-pw")
    public boolean changePw(@RequestBody ChangePwRequest req) {
        req.setChangedPw(encoder.defaultEncoder().encode(req.getChangedPw()));
        return userService.changePw(req);
    }

    @PostMapping("/check-pw")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public boolean checkPw(@RequestBody CheckPwRequest req) {
        return userService.checkPw(req);
    }
}
