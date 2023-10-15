package com.self.music.member.web.controller;

import com.self.music.member.web.dto.MemberSaveDto.MemberSaveRequestDto;
import com.self.music.member.web.dto.MemberSaveDto.MemberSaveResponseDto;
import com.self.music.member.web.service.MemberSaveProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberCommendApi {
    private final MemberSaveProxyService memberSaveProxyService;

    @PostMapping("")
    public MemberSaveResponseDto memberSave(@RequestBody MemberSaveRequestDto dto) {
        return memberSaveProxyService.memberSave(dto);
    }
}
