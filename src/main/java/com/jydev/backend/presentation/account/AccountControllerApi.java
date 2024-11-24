package com.jydev.backend.presentation.account;

import com.jydev.backend.application.account.RegisterAccountUseCase;
import com.jydev.backend.core.error.ErrorCode;
import com.jydev.backend.core.web.ErrorResponse;
import com.jydev.backend.presentation.account.model.request.RegisterAccountRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountControllerApi implements AccountApi {

    private final RegisterAccountUseCase registerAccountUseCase;

    @Override
    public void registerAccount(RegisterAccountRequest request) {
        registerAccountUseCase.register(
                request.userId(),
                request.password(),
                request.name()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.debug(ex.getMessage());
        return new ErrorResponse("Account is already registered", ErrorCode.A01003);
    }
}
