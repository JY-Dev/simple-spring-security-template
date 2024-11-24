package com.jydev.backend.domain.user;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class UserPrivacy {
    private String name;

    private UserPrivacy(String name) {
        this.name = name;
    }

    public static UserPrivacy create(String name, PrivacyEncryptor encryptor) {

        var encryptedName = encryptor.encrypt(name);
        return new UserPrivacy(encryptedName);
    }
}
