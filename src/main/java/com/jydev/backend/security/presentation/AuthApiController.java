package com.jydev.backend.security.presentation;

import com.jydev.backend.security.presentation.model.request.LoginRequest;
import com.jydev.backend.security.presentation.model.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthApiController implements AuthApi {

    @Override
    public LoginResponse login(LoginRequest request) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
