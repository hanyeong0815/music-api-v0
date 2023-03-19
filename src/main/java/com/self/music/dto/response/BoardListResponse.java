package com.self.music.dto.response;

import com.self.music.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class BoardListResponse {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoardListRes {
        private List<Board> boardList;
        private int totalPage;
        private Long totalElements;
    }

}
