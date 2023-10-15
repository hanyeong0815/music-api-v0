package com.self.music.fileuploader;

import lombok.Getter;

@Getter
public abstract class S3Buket {
    private final String buket;

    protected S3Buket(String buket) {
        this.buket = buket;
    }

    @Override
    public String toString() {
        return buket;
    }
}