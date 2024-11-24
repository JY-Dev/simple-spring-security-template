package com.jydev.backend.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.Base64;

@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "encryption.aes")
public class AesEncryptionProperties {

    @NotBlank
    private String secretKey;

    @NotBlank
    private String iv;

    public byte[] getSecretKey() {
        return Base64.getDecoder().decode(secretKey);
    }

    public byte[] getIv() {
        return Base64.getDecoder().decode(iv);
    }
}