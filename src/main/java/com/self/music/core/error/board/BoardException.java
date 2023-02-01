package com.self.music.core.error.board;

import com.self.music.core.error.ErrorCode;

public class BoardException extends RuntimeException{
    public final ErrorCode ERROR_CODE;

    public BoardException(ErrorCode errorCode) {
        super(errorCode.defaultMessage());
        this.ERROR_CODE = errorCode;
    }

    public BoardException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.defaultMessage(), cause);
        this.ERROR_CODE = errorCode;
    }

    public BoardException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.ERROR_CODE = errorCode;
    }

    public BoardException(String message, Throwable cause) {
        super(message, cause);
        this.ERROR_CODE = BoardErrorCode.ETC;
    }
}
