package com.jydev.backend.core.web;

import com.jydev.backend.core.error.ErrorCode;

public record ErrorResponse(
        String message,
        ErrorCode errorCode
) {
}
