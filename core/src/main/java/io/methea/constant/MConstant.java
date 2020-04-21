package io.methea.constant;

/**
 * Author : DKSilverX
 * Date : 9/8/2019
 */
public class MConstant {
    private MConstant() {
    }

    public static final String CONTEXT_PATH_KEY = "contextPath";

    // freemarker template
    public static final String CORE_TEMPLATE_KEY = "methea.core.freemarker.templates";
    public static final String CLIENT_TEMPLATE_KEY = "methea.client.freemarker.templates";

    // security config
    public static final String CLIENT_SECRET_KEY = "methea.client.security.secret";
    public static final String CLIENT_TOKEN_EXPIRATION = "methea.client.security.token.expiration_time";
    public static final String CLIENT_TOKEN_PREFIX = "methea.client.security.token.prefix";
    public static final String CLIENT_REQUEST_HEADER_KEY = "methea.client.security.request.header";

    public static final String COMMA = ",";
    public static final String SPACE = " ";

    // cache datatable meta data
    public static final String COLUMNS_LABEL = "_COLUMNS_LABEL";
    public static final String COLUMNS_KEY = "_COLUMNS_KEY";
    public static final String COLUMNS_FILTER = "_COLUMNS_FILTER";

    // cache dropdown data
    public static final String DROPDOWN = "mDropdown";
    public static final String ACCOUNT_DROPDOWN = "accountDropdown";

    // security filter
    public static final String SLASH = "/";
    public static final String SLASH_STAR = "/**";
    public static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

    // webservice
    public static final String JSON_STATUS = "status";
    public static final String JSON_MESSAGE = "message";
    public static final String JSON_DATA = "data";
}
