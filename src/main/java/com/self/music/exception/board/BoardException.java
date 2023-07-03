package com.self.music.exception.board;

import com.self.music.common.support.exception.CustomException;
import com.self.music.common.support.exception.ErrorCode;

public class BoardException extends CustomException {
    public BoardException() {
        super();
    }

    public BoardException(String message) {
        super(message);
    }

    public BoardException(String message, Throwable cause) {
        super(message, cause);
    }

    public BoardException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BoardException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
