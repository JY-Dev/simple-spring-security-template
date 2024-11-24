package com.jydev.backend.security.auth.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jydev.backend.security.presentation.model.response.LoginResponse;
import com.jydev.backend.security.token.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtHelper jwtHelper;
    private final ObjectMapper objectMapper;


    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {

        try {
            var user = (LoginAccount) authentication.getPrincipal();

            var accessToken = jwtHelper.issueToken(user.getId());

            log.debug("로그인 토큰 발급 성공 : {}", user.getId());

            var loginResponse = new LoginResponse(user.getId(), accessToken);
            var responseJson = objectMapper.writeValueAsString(loginResponse);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(responseJson);
        } catch (Exception e) {

            log.error("로그인 토큰 발급 에러", e);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("서버 내부 오류");
        }

    }

}
