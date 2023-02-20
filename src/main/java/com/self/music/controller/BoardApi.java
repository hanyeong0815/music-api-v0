package com.self.music.controller;

import com.self.music.domain.enums.FileType;
import com.self.music.dto.request.BoardUpload.BoardUploadRequest;
import com.self.music.service.AwsS3Service;
import com.self.music.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardApi {
    private final BoardService boardService;
    private final AwsS3Service s3Service;

    @PostMapping("upload")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public boolean insertBoard(@RequestPart(value = "data", required = false) BoardUploadRequest data,
                               @RequestPart(value = "music", required = false) MultipartFile music,
                               @RequestPart(value = "cover", required = false) MultipartFile cover,
                               HttpServletRequest request) {
        Instant now = Instant.now();
        String musicUrl = s3Service.uploadFile(FileType.MUSIC, music);
        String coverUrl = null;
        if (cover != null) {
            coverUrl = s3Service.uploadFile(FileType.COVER, cover);
        }
        return boardService.insertNewBoard(data.toEntity(request.getRemoteAddr(), now, musicUrl, coverUrl));
    }
}
