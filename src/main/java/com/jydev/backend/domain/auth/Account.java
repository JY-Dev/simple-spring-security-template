package com.jydev.backend.domain.auth;

import com.jydev.backend.core.jpa.TimeAuditableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ACCOUNT")
@Entity
public class Account extends TimeAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "PASSWORD")
    private String password;


    @Builder
    public Account(String loginId, String password, long userId) {

        if(!StringUtils.hasText(loginId)) {
            throw new IllegalArgumentException("loginId must not be empty");
        }

        if(!StringUtils.hasText(password)) {
            throw new IllegalArgumentException("password must not be empty");
        }

        this.userId = userId;
        this.loginId = loginId;
        this.password = password;
    }

}
