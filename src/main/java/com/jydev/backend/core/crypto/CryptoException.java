package com.jydev.backend.core.crypto;

public class CryptoException extends RuntimeException {
    public CryptoException(String message) {
        super(message);
    }
    public CryptoException(String message, Throwable cause) {
        super(message, cause);
    }
}
