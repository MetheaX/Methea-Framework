package io.methea.config.jwt;

/**
 * Author : DKSilverX
 * Date : 13/04/2020
 */
public class JWTConstants {
    private JWTConstants(){}
    static final String EXPIRATION_TIME = "7200000"; // 2 Hours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
