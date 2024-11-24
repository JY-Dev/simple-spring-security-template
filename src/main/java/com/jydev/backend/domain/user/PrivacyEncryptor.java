package com.jydev.backend.domain.user;

public interface PrivacyEncryptor {
    String encrypt(String plainText);
    String decrypt(String encryptedText);
}
