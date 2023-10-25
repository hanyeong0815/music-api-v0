package com.self.music.profile.application.exception;

import com.self.music.common.support.exception.CustomException;
import com.self.music.common.support.exception.ErrorCode;

public class ProfileException extends CustomException {
    public ProfileException() {
        super();
    }

    public ProfileException(String message) {
        super(message);
    }

    public ProfileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProfileException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ProfileException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
