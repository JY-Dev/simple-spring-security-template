package com.jydev.backend.core.error;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode {
    DEFAULT("미정"),

    // A01 (계정)
    A01001("등록되지 않은 사용자"),
    A01002("로그인 아이디 중복"),
    A01003("이미 가입된 사용자"),
    ;

    private final String description;
}
