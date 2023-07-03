package com.self.music.exception.board;

import com.self.music.common.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum BoardErrorCode implements ErrorCode {
    INVALID_BOARD("유효하지 않은 입력양식입니다.", HttpStatus.BAD_REQUEST),
    DEFAULT("board 관련 오류", HttpStatus.INTERNAL_SERVER_ERROR);

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
        return new BoardException(this);
    }

    @Override
    public RuntimeException defaultException(Throwable cause) {
        return new BoardException(this, cause);
    }
}
