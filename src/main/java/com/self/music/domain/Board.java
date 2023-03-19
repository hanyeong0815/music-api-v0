package com.self.music.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "board")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    private  String id;

    private Long userId;

    private String userName;

    private String title;

    private String lyrics;

    private Instant uploadDate;

    private String uploadIp;

    private String imgUrl;

    private String musicUrl;

    @Override
    public String toString() {
        return "Board{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", lyrics='" + lyrics + '\'' +
                ", uploadDate=" + uploadDate +
                ", uploadIp='" + uploadIp + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", musicUrl='" + musicUrl + '\'' +
                '}';
    }
}