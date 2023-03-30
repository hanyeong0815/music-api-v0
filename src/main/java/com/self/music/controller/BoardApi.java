package com.self.music.controller;

import com.self.music.core.error.Preconditions;
import com.self.music.core.error.board.BoardErrorCode;
import com.self.music.core.error.member.MemberErrorCode;
import com.self.music.domain.Board;
import com.self.music.domain.enums.FileType;
import com.self.music.dto.request.BoardUpload.BoardUploadRequest;
import com.self.music.dto.response.BoardListResponse.BoardListRes;
import com.self.music.dto.response.BoardResponse;
import com.self.music.service.AwsS3Service;
import com.self.music.service.BoardService;
import com.self.music.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@RestController
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardApi {
    private final BoardService boardService;
    private final UserService userService;
    private final AwsS3Service s3Service;

    @PostMapping("upload")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public boolean insertBoard(@RequestPart(value = "data", required = false) BoardUploadRequest data,
                               @RequestPart(value = "music", required = false) MultipartFile music,
                               @RequestPart(value = "cover", required = false) MultipartFile cover,
                               HttpServletRequest request) {
        Preconditions.validate(
                data.getUserId() != null || data.getTitle() != null || music != null,
                BoardErrorCode.INVALID_BOARD
        );

        String userName = userService.findUserNameById(data.getUserId());
        Preconditions.validate(
                userName != null,
                MemberErrorCode.NO_SUCH_USER
        );

        Instant now = Instant.now();
        String musicUrl = s3Service.uploadFile(FileType.MUSIC, music);
        String coverUrl = null;
        
        if (cover != null) {
            coverUrl = s3Service.uploadFile(FileType.COVER, cover);
        }

        Board newBoard = Board.builder()
                .userId(data.getUserId())
                .userName(userName)
                .uploadIp(request.getRemoteAddr())
                .uploadDate(now)
                .title(data.getTitle())
                .lyrics(data.getLyrics())
                .musicUrl(musicUrl)
                .imgUrl(coverUrl)
                .build();

        return boardService.insertNewBoard(newBoard);
    }

    @GetMapping("/play-music")
    public BoardResponse findByBoardId(String boardId) {
        return boardService.findById(boardId);
    }

    @GetMapping("list")
    public BoardListRes findAllBoard (int page) {
        PageRequest pageable = PageRequest.of(page, 10);
        return boardService.findAllPagination(pageable);
    }
}
