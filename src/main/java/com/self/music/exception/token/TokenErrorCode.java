package com.self.music.exception.token;

import com.self.music.common.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum TokenErrorCode implements ErrorCode {
    NO_SUCH_REFRESH_TOKEN("refresh token이 존재하지 않습니다.", HttpStatus.UNAUTHORIZED),
    DEFAULT("토큰 관련 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    public final String MESSAGE;
    public final HttpStatus STATUS;

    @Override
    public HttpStatus defaultHttpStatus() {
        return STATUS;
    }

    @Override
    public String defaultMessage() {
        return MESSAGE;
    }

    @Override
    public RuntimeException defaultException() {
        return new TokenException(this);
    }

    @Override
    public RuntimeException defaultException(Throwable cause) {
        return new TokenException(this, cause);
    }
}
