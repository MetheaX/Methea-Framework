package io.methea.constant;

/**
 * Author : DKSilverX
 * Date : 9/8/2019
 */
public class MConstant {
    private MConstant() {
    }

    public static final String RSA = "RSA";
    public static final String CERT_TYPE = "CERT_1";
    public static final int MIN_KEY_SIZE = 2048;
    public static final int THREE_KEY_SIZE = 3072;
    public static final int FOUR_KEY_SIZE = 4096;
    public static final String SEPARATOR = "\\.";

    public static final String CONTEXT_PATH_KEY = "contextPath";

    // freemarker template
    public static final String CORE_TEMPLATE_KEY = "methea.core.freemarker.templates";
    public static final String CLIENT_TEMPLATE_KEY = "methea.client.freemarker.templates";

    // security config
    public static final String CLIENT_TOKEN_EXPIRATION = "methea.client.security.token.expiration_time";
    public static final String CLIENT_REQUEST_HEADER_KEY = "methea.client.security.request.header";

    public static final String COMMA = ",";
    public static final String SPACE = " ";
    public static final String ACTIVE_STATUS = "A";
    public static final String INACTIVE_STATUS = "I";
    public static final String YES = "Y";
    public static final String NO = "N";

    // cache datatable meta data
    public static final String COLUMNS_LABEL = "_COLUMNS_LABEL";
    public static final String COLUMNS_KEY = "_COLUMNS_KEY";
    public static final String COLUMNS_FILTER = "_COLUMNS_FILTER";

    // cache dropdown data
    public static final String DROPDOWN = "mDropdown";
    public static final String ACCOUNT_DROPDOWN = "accountDropdown";
    public static final String USER_DROPDOWN = "userDropdown";
    public static final String GROUP_DROPDOWN = "groupDropdown";
    public static final String ROLE_DROPDOWN = "roleDropdown";
    public static final String URI_DROPDOWN = "uriDropdown";
    public static final String API_URL_DROPDOWN = "apiUrlDropdown";

    // security filter
    public static final String SLASH = "/";
    public static final String SLASH_STAR = "/**";
    public static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

    // webservice
    public static final String VERIFY_CODE = "verify_code";
    public static final String JWT_TOKEN = "jwt_token";
    public static final String EXPIRED_IN = "expired_in";
    public static final String JSON_STATUS = "status";
    public static final String JSON_MESSAGE = "message";
    public static final String JSON_DATA = "data";
}
