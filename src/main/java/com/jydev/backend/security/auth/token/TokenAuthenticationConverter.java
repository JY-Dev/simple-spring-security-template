package com.jydev.backend.security.auth.token;

import com.jydev.backend.security.token.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;


public class TokenAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {

        var bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        var accessToken = JwtHelper.resolveBearerToken(bearerToken);

        if (accessToken == null) {
            return null;
        }

        return new TokenAuthentication(accessToken);
    }

}
