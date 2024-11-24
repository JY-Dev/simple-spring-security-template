package com.jydev.backend.security.auth.token;

import com.jydev.backend.security.auth.AuthenticatedUser;
import com.jydev.backend.security.token.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtHelper jwtHelper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (authentication == null) return null;

        var userAuthentication = (TokenAuthentication) authentication;
        var accessToken = userAuthentication.accessToken();

        if (!jwtHelper.isValidToken(accessToken)) {
            return null;
        }

        var userId = jwtHelper.getUserIdFromToken(accessToken);
        return new AuthenticatedUser(userId);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.isAssignableFrom(authentication);
    }
}
