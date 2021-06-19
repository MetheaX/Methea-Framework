package io.github.metheax.exception;

public class UserNoActiveSessionException extends RuntimeException {
    public UserNoActiveSessionException(String s) {
        super(s);
    }
}
