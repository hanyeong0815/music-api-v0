package com.self.music.fileuploader;

import com.self.music.domain.enums.FileType;

public class S3BuketFactory {
    public static S3Buket get(FileType fileType) {
        S3Buket buket = null;

        switch (fileType) {
            case MUSIC:
                buket = S3MusicBuket.getInstance();
                break;
            case COVER:
                buket = S3CoverBuket.getInstance();
                break;
            default:
        }

        return buket;
    }
}
