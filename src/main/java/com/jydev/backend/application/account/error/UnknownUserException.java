package com.jydev.backend.application.account.error;

import com.jydev.backend.core.error.BusinessException;
import com.jydev.backend.core.error.ErrorCode;

public class UnknownUserException extends BusinessException {
    public UnknownUserException(String message) {
        super(message, ErrorCode.A01001);
    }
}
