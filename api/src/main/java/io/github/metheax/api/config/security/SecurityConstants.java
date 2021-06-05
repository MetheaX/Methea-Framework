package io.github.metheax.api.config.security;

/**
 * Author : DKSilverX
 * Date : 13/04/2020
 */
public class SecurityConstants {
    private SecurityConstants(){}
    public static String EXPIRATION_ACCESS_TOKEN_TIME = "1800000"; // 30 MIN
    public static String EXPIRATION_REF_TOKEN_TIME = "43200000"; // 12 Hours
    public static String TOKEN_PREFIX = "Bearer ";
    public static String HEADER_STRING = "Authorization";
}
