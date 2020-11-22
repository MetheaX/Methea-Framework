package io.methea.exception;

public class UserNoActiveSessionException extends RuntimeException {
    public UserNoActiveSessionException(String s) {
        super(s);
    }
}
