package com.self.music.core.error.member;

import com.self.music.core.error.ErrorCode;

import java.text.MessageFormat;

public enum MemberErrorCode implements ErrorCode {
    ILLEGAL_PK("알맞지 않은 기본키"),
    DUPLICATED_USERNAME("Username already exists."),
    NO_SUCH_USER("해당 유저가 존재하지 않음."),
    AUTH_TOKEN_MISMATCHED("인증 토큰 불일치"),
    REFRESH_TOKEN_MISMATCHED("리프레시 토큰 불일치"),
    UNAUTHORIZED("미인증 사용자"),
    FORBIDDEN("권한 불충족"),
    INVALID_USERNAME("계정 양식 불일치"),
    INVALID_PASSWORD("암호 양식 불일치"),
    INVALID_ARGUMENTS("기타 입력 항목의 양식이 일치하지 않음."),
    ETC("회원 관련 기타 오류");

    public final String MESSAGE;

    MemberErrorCode(String message) {
        this.MESSAGE = message;
    }

    @Override
    public MemberException defaultException() {
        return new MemberException(this);
    }

    @Override
    public String defaultMessage() {
        return MessageFormat.format("[{0}] {1}", this.name(), MESSAGE);
    }
}
