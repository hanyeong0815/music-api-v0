package com.self.music.fileuploader;

public class S3CoverBuket extends S3Buket {
    private S3CoverBuket() {
        super(
                "music-yoehadu/coverimg"
        );
    }

    public static S3Buket getInstance() {
        return LazyHolder.IT;
    }

    private static class LazyHolder {
        private static final S3CoverBuket IT = new S3CoverBuket();
    }
}
