package com.self.music.core.error.board;

import com.self.music.core.error.ErrorCode;

import java.text.MessageFormat;

public enum BoardErrorCode implements ErrorCode {
    AUTH_TOKEN_MISMATCHED("인증 토큰 불일치"),
    UNAUTHORIZED("미인증 사용자"),
    FORBIDDEN("권한 불충족"),
    INVALID_BOARD("업로드 양식 불일치"),
    INVALID_TITLE("제목 양식 불일치"),
    INVALID_FILE("파일 양식 불일치"),
    TOO_LARGE_FILE("파일 크기가 너무 큼"),
    ETC("게시판 관련 기타 오류");

    public final String MESSAGE;

    BoardErrorCode(String message) {
        this.MESSAGE = message;
    }

    @Override
    public RuntimeException defaultException() {
        return new BoardException(this);
    }

    @Override
    public String defaultMessage() {
        return MessageFormat.format("[{0}] {1}", this.name(), MESSAGE);
    }
}
