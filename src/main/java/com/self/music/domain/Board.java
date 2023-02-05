package com.self.music.domain;

import com.self.music.utills.domain.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.sql.Blob;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board extends BaseEntity {
    @Column
    private Long userId;

    @Column
    private String musicName;

    @Column
    private OffsetDateTime uploadDate;

    @Column
    private String uploadIp;

    @Column
    @Lob
    private byte[] imgBlob;

    @Column
    @Lob
    private byte[] musicBlob;
}