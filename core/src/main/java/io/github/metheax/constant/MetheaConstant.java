package io.github.metheax.constant;

/**
 * Author : Kuylim Tith
 * Date : 9/8/2019
 */
public class MetheaConstant {
    private MetheaConstant() {
    }

    public static final String RSA = "RSA";
    public static final String CERT_TYPE = "CERT_1";
    public static final String CERT_TYPE_2 = "CERT_2";
    public static final int MIN_KEY_SIZE = 2048;
    public static final int THREE_KEY_SIZE = 3072;
    public static final int FOUR_KEY_SIZE = 4096;
    public static final String SEPARATOR = "\\.";

    public static final String CONTEXT_PATH_KEY = "contextPath";

    public static final String PUBLIC_USER = "PUBLIC_USER";

    // security config
    public static final String CLIENT_TOKEN_EXPIRATION = "methea.client.security.token.expiration_time";
    public static final String CLIENT_REQUEST_HEADER_KEY = "methea.client.security.request.header";

    // freemarker template
    public static final String CORE_TEMPLATE_KEY = "methea.core.freemarker.templates";
    public static final String CLIENT_TEMPLATE_KEY = "methea.client.freemarker.templates";

    public static final String ID = "id";
    public static final String COMMA = ",";
    public static final String COLON = ":";
    public static final String SPACE = " ";
    public static final String ACTIVE_STATUS = "A";
    public static final String INACTIVE_STATUS = "I";
    public static final String YES = "Y";
    public static final String NO = "N";
    public static final String PARENT = "P";
    public static final String CORE_MENU = "coreMenu";

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
    public static final String YES_NO_DROPDOWN = "yesNoDropdown";
    public static final String MENU_DROPDOWN = "menuDropdown";
    public static final String HTTP_METHOD_DROPDOWN = "httpMethodDropdown";

    // security filter
    public static final String SLASH = "/";
    public static final String SLASH_STAR = "/**";
    public static final String DOUBLE_STAR = "**";
    public static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

    // webservice
    public static final String VERIFY_CODE = "verify_code";
    public static final String JWT_TOKEN = "jwt_token";
    public static final String EXPIRED_IN = "expired_in";
    public static final String JSON_STATUS = "status";
    public static final String JSON_MESSAGE = "message";
    public static final String JSON_DATA = "data";

    // http methods
    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final String PUT = "PUT";
    public static final String PATCH = "PATCH";
    public static final String DELETE = "DELETE";

    // argon
    public static final int SALT_LENGTH = 64;
    public static final int HASH_LENGTH = 128;
    public static final int PARALLELISM = 8;
    public static final int MEMORY = 4096;
    public static final int ITERATIONS = 5;
    public static final String ARGON_PREFIX_SPLIT = "\\$argon2id\\$v=19\\$m=4096,t=5,p=8\\$";
    public static final String ARGON_PREFIX = "$argon2id$v=19$m=4096,t=5,p=8$";
}
