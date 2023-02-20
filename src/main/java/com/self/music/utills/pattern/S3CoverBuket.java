package com.self.music.utills.pattern;

public class S3CoverBuket extends S3Buket{
    private S3CoverBuket() {
        super(
                "music-yoehadu/coverimg"
        );
    }

    public static S3Buket getInstance() {
        return S3CoverBuket.LazyHolder.IT;
    }

    private static class LazyHolder {
        private static final S3CoverBuket IT = new S3CoverBuket();
    }
}
