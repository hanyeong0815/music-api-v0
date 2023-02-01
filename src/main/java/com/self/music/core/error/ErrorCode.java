package com.self.music.core.error;

public interface ErrorCode {
    RuntimeException defaultException();

    String defaultMessage();
}
