package com.self.music.dto.request;

import com.self.music.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

public class BoardUpload {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardUploadRequest {
        private Long userId;
        private String title;
        private String lyrics;

        @Override
        public String toString() {
            return "BoardUploadRequest{" +
                    "userId=" + userId +
                    ", title='" + title + '\'' +
                    ", lyrics='" + lyrics + '\'' +
                    '}';
        }
    }
}
