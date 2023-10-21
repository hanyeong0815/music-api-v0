package com.self.music.member.web.controller;

import com.self.music.member.web.dto.MemberLoginDto.MemberLoginResponseDto;
import com.self.music.member.web.service.MemberLoginProxyService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberQueryApi {
    private final MemberLoginProxyService memberLoginProxyService;

    @GetMapping("login")
    public MemberLoginResponseDto login(Authentication authentication, HttpServletResponse response) {
        return memberLoginProxyService.login(authentication, response);
    }
}
