package com.jydev.backend.security.auth.login;

import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Getter
public class LoginAccount extends User {

    private final long id;

    public LoginAccount(String loginId, String password, long userId) {
        super(loginId, password, AuthorityUtils.NO_AUTHORITIES);
        this.id = userId;
    }
}
