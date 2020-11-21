package io.methea.exception;

public class InvalidClientSecretException extends RuntimeException {
    public InvalidClientSecretException(String s) {
        super(s);
    }
}
