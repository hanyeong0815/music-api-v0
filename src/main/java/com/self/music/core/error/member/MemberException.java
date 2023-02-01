package com.self.music.core.error.member;

import com.self.music.core.error.ErrorCode;

public class MemberException extends RuntimeException {

    public final ErrorCode ERROR_CODE;

    public MemberException(ErrorCode errorCode) {
        super(errorCode.defaultMessage());
        this.ERROR_CODE = errorCode;
    }

    public MemberException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.defaultMessage(), cause);
        this.ERROR_CODE = errorCode;
    }

    public MemberException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.ERROR_CODE = errorCode;
    }

    public MemberException(String message, Throwable cause) {
        super(message, cause);
        this.ERROR_CODE = MemberErrorCode.ETC;
    }
}
