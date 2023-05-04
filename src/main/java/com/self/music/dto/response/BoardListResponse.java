package com.self.music.dto.response;

import com.self.music.domain.Board;
import lombok.*;

import java.time.Instant;
import java.util.List;

public class BoardListResponse {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoardListRes {
        private List<BoardRes> boardList;
        private int totalPage;
        private Long totalElements;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoardRes {
        private String id;
        private Long userId;
        private String userName;
        private String title;
        private Instant uploadDate;
        private String uploadIp;
        private String imgUrl;
    }
}
