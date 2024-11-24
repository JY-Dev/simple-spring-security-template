package com.jydev.backend.application.account.error;

import com.jydev.backend.core.error.BusinessException;
import com.jydev.backend.core.error.ErrorCode;

public class DuplicateLoginIdException extends BusinessException {

    public DuplicateLoginIdException(String message) {
        super(message, ErrorCode.A01002);
    }
}
