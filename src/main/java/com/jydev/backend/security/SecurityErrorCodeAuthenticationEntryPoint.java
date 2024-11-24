package com.jydev.backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jydev.backend.core.error.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class SecurityErrorCodeAuthenticationEntryPoint extends SecurityErrorCodeResponseHandler implements AuthenticationEntryPoint {

    public SecurityErrorCodeAuthenticationEntryPoint(final ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException, ServletException {
        var status = HttpServletResponse.SC_UNAUTHORIZED;
        var errorCode = ErrorCode.DEFAULT;

        handle(request, response, errorCode, authException.getMessage(), status);
    }

}
