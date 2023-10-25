package com.self.music.profile.application.exception;

import com.self.music.common.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ProfileErrorCode implements ErrorCode {
    ALREADY_USED("이미 존재하는 프로필입니다.", HttpStatus.CONFLICT),
    NO_SUCH_USER("존재하지 않는 유저입니다.", HttpStatus.BAD_REQUEST),
    DEFAULT("", HttpStatus.INTERNAL_SERVER_ERROR);

    public final String message;
    public final HttpStatus status;

    @Override
    public HttpStatus defaultHttpStatus() {
        return status;
    }

    @Override
    public String defaultMessage() {
        return message;
    }

    @Override
    public ProfileException defaultException() {
        return new ProfileException(this);
    }

    @Override
    public ProfileException defaultException(Throwable cause) {
        return new ProfileException(this, cause);
    }
}
