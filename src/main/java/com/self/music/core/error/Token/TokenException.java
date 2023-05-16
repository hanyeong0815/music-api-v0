package com.self.music.core.error.Token;

import com.self.music.core.error.ErrorCode;
import com.self.music.core.error.member.MemberErrorCode;

public class TokenException extends RuntimeException {
    public final ErrorCode ERROR_CODE;

    public TokenException(ErrorCode errorCode) {
        super(errorCode.defaultMessage());
        this.ERROR_CODE = errorCode;
    }

    public TokenException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.defaultMessage(), cause);
        this.ERROR_CODE = errorCode;
    }

    public TokenException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.ERROR_CODE = errorCode;
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
        this.ERROR_CODE = MemberErrorCode.ETC;
    }
}
