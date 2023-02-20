package com.self.music.dto.request;

import com.self.music.domain.Board;
import com.self.music.dto.request.ChangePwDto.ChangePwRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.time.Instant;
import java.time.OffsetDateTime;

public class BoardUpload {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardUploadRequest {
        private Long userId;
        private String title;
        private String lyrics;

        public Board toEntity(String uploadIp, Instant uploadDate, String music, String cover) {
            return Board.builder()
                    .userId(this.userId)
                    .uploadIp(uploadIp)
                    .uploadDate(uploadDate)
                    .title(this.title)
                    .lyrics(this.lyrics)
                    .musicUrl(music)
                    .imgUrl(cover)
                    .build();
        }

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
