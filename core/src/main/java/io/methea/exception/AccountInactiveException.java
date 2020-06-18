package io.methea.exception;

/**
 * Author : DKSilverX
 * Date : 18/06/2020
 */
public class AccountInactiveException extends RuntimeException {
    public AccountInactiveException(String s) {
        super(s);
    }
}
