package com.self.music.fileuploader;

public class S3MusicBuket extends S3Buket{
    private S3MusicBuket() {
        super(
                "music-yoehadu/music"
        );
    }

    public static S3Buket getInstance() {
        return LazyHolder.IT;
    }

    private static class LazyHolder {
        private static final S3MusicBuket IT = new S3MusicBuket();
    }
}
