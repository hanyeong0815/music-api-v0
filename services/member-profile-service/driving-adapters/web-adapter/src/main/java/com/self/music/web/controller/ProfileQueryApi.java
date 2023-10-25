package com.self.music.web.controller;

import com.self.music.profile.domain.Profile;
import com.self.music.web.service.ProfileDetailViewProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("profile")
@RequiredArgsConstructor
public class ProfileQueryApi {
    private final ProfileDetailViewProxyService profileDetailViewProxyService;

    @GetMapping("/{username}")
    public Profile findProfileDetailView(@PathVariable("username") String username) {
        return profileDetailViewProxyService.findProfileDetailView(username);
    }
}
