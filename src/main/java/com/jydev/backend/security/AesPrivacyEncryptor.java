package com.jydev.backend.security;

import com.jydev.backend.config.AesEncryptionProperties;
import com.jydev.backend.core.crypto.AES256CryptoUtil;
import com.jydev.backend.core.crypto.CryptoException;
import com.jydev.backend.domain.user.PrivacyEncryptor;
import org.springframework.stereotype.Component;

@Component
public class AesPrivacyEncryptor implements PrivacyEncryptor {

    private final byte[] secretKey;
    private final byte[] iv;

    public AesPrivacyEncryptor(AesEncryptionProperties properties) {
        this.secretKey = properties.getSecretKey();
        this.iv = properties.getIv();
    }

    @Override
    public String encrypt(String plainText) {
        try {
            return AES256CryptoUtil.encryptText(plainText, secretKey, iv);
        } catch (Exception e) {
            throw new CryptoException("Error during encryption", e);
        }
    }

    @Override
    public String decrypt(String encryptedText) {
        try {
            return AES256CryptoUtil.decryptText(encryptedText, secretKey, iv);
        } catch (Exception e) {
            throw new CryptoException("Error during decryption", e);
        }
    }
}