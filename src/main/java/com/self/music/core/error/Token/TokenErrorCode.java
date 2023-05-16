package com.self.music.core.error.Token;

import com.self.music.core.error.ErrorCode;

import java.text.MessageFormat;

public enum TokenErrorCode implements ErrorCode {
    NO_SUCH_REFRESH_TOKEN("토큰이 존재하지 않습니다.");

    public final String MESSAGE;

    TokenErrorCode(String message) {
        this.MESSAGE = message;
    }

    @Override
    public TokenException defaultException() {
        return new TokenException(this);
    }

    @Override
    public String defaultMessage() {
        return MessageFormat.format("[{0}] {1}", this.name(), MESSAGE);
    }
}
