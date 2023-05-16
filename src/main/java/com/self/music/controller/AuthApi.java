package com.self.music.controller;

import com.self.music.dto.request.TokenReissueRequest;
import com.self.music.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthenticationService authenticationService;

    @GetMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody TokenReissueRequest req) {
        return ResponseEntity.ok(authenticationService.reissue(req));
    }
}
