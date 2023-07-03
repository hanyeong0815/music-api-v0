package com.self.music.exception.user;

import com.self.music.common.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    USERNAME_ALREADY_USED("이미 사용 중인 계정입니다.", HttpStatus.CONFLICT),
    INVALID_USERNAME_OR_PASSWORD("유효하지 않은 아이디 또는 비밀번호 입니다", HttpStatus.UNAUTHORIZED),
    NO_SUCH_USER("존재하지 않는 유저입니다.", HttpStatus.BAD_REQUEST),
    DEFAULT("회원 관련 오류", HttpStatus.INTERNAL_SERVER_ERROR);

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
        return new UserException(this);
    }

    @Override
    public RuntimeException defaultException(Throwable cause) {
        return new UserException(this, cause);
    }
}
