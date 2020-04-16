package io.methea.constant;

/**
 * Author : DKSilverX
 * Date : 9/8/2019
 */
public class MConstant {
    private MConstant() {
    }

    public static final String CORE_TEMPLATE_KEY = "methea.core.freemarker.templates";
    public static final String CLIENT_TEMPLATE_KEY = "methea.client.freemarker.templates";
    public static final String CLIENT_SECRET_KEY = "methea.client.security.secret";
    public static final String CLIENT_TOKEN_EXPIRATION = "methea.client.security.token.expiration_time";
    public static final String CLIENT_TOKEN_PREFIX = "methea.client.security.token.prefix";
    public static final String CLIENT_REQUEST_HEADER_KEY = "methea.client.security.request.header";

    public static final String COMMA = ",";
    public static final String SPACE = " ";

    // cache master data
    public static final String COLUMNS_LABEL = "_COLUMNS_LABEL";
    public static final String COLUMNS_KEY = "_COLUMNS_KEY";
    public static final String COLUMNS_FILTER = "_COLUMNS_FILTER";

    // security filter
    public static final String SLASH = "/";
    public static final String SLASH_STAR = "/**";
    public static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";
}
