package io.methea.config.jwt;

/**
 * Author : DKSilverX
 * Date : 13/04/2020
 */
public class JWTConstants {
    private JWTConstants(){}
    static final String SECRET = "S'jG,i:bM3'ol}Di4s@7&O=r}x+Rs[[#&Fhj8{X_5^v}*CftMIE4ZwFX3z0XN]{";
    static final String EXPIRATION_TIME = "8600000"; // 1 days
    static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
}
