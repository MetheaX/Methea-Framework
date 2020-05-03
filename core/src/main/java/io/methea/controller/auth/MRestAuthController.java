package io.methea.controller.auth;

import io.methea.config.jwt.JWTConstants;
import io.methea.constant.MConstant;
import io.methea.domain.webservice.dto.ClientAuthentication;
import io.methea.domain.webservice.dto.TokenInfo;
import io.methea.utils.SystemUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.methea.constant.MConstant.VERIFY_CODE;

/**
 * Author : DKSilverX
 * Date : 8/24/2019
 */
@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MRestAuthController {

    private static Logger log = LoggerFactory.getLogger(MRestAuthController.class);
    private static final String GET_ACCESS_TOKEN_URL = "/auth/token";

    private final RestTemplate restTemplate;
    private final Environment env;

    @Inject
    public MRestAuthController(RestTemplate restTemplate, @Lazy Environment env) {
        this.restTemplate = restTemplate;
        this.env = env;
    }

    @PostMapping(value = GET_ACCESS_TOKEN_URL)
    public ResponseEntity<Map<String, Object>> generateToken(@RequestBody ClientAuthentication client, HttpServletRequest req) {

        Map<String, Object> map = new HashMap<>();
        client.setVerifyCode(req.getHeader(VERIFY_CODE));
        HttpEntity<ClientAuthentication> request = new HttpEntity<>(client);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(SystemUtils.getBaseUrl(req).concat("login"),
                    request, String.class);
            String tokenKey = ObjectUtils.isEmpty(env.getProperty(MConstant.CLIENT_REQUEST_HEADER_KEY)) ? JWTConstants.HEADER_STRING
                    : env.getProperty(MConstant.CLIENT_REQUEST_HEADER_KEY);
            if (ObjectUtils.isEmpty(response.getHeaders().get(tokenKey))) {
                map.put(MConstant.JSON_MESSAGE, "Bad request, please contact support team!!!");
                map.put(MConstant.JSON_STATUS, 400);
            }
            TokenInfo token = new TokenInfo(Objects.requireNonNull(response.getHeaders().get(tokenKey)).get(0)
                    .concat(".").concat(Objects.requireNonNull(response.getHeaders().get(VERIFY_CODE)).get(0)),
                    StringUtils.EMPTY, Objects.requireNonNull(response.getHeaders().get(MConstant.EXPIRED_IN)).get(0));
            map.put("token", token);
            map.put(MConstant.JSON_MESSAGE, "Access token generated!!!");
            map.put(MConstant.JSON_STATUS, 200);
        } catch (Exception ex) {
            log.error(">>>>> Generate access token error: ", ex);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
