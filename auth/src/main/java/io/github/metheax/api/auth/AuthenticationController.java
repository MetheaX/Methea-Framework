package io.github.metheax.api.auth;

import io.github.metheax.api.domain.RefreshTokenPayload;
import io.github.metheax.api.domain.RequestTokenPayload;
import io.github.metheax.api.domain.Token;
import io.github.metheax.api.service.MetheaAuthenticationService;
import io.github.metheax.api.domain.RevokeTokenPayload;
import io.github.metheax.constant.MetheaConstant;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : Kuylim Tith
 * Date : 08/08/2020
 */
@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    private static final String GET_ACCESS_TOKEN_URL = "/auth/token";
    private static final String VERIFY_REFRESH_TOKEN = "/auth/refresh/token";
    private static final String REVOKE_ACCESS_TOKEN_URL = "/auth/token/revoke";

    private final MetheaAuthenticationService authenticationService;

    @Inject
    public AuthenticationController(MetheaAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = GET_ACCESS_TOKEN_URL, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> generateTokenFromUser(@RequestBody RequestTokenPayload client, HttpServletRequest req) {
        Map<String, Object> map = new HashMap<>();
        try {
            Token token = authenticationService.generateTokenFromUser(client, req);

            if (ObjectUtils.isEmpty(token)) {
                map.put(MetheaConstant.JSON_MESSAGE, "Invalid account!!");
                map.put(MetheaConstant.JSON_STATUS, 400);
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
            map.put("token", token);
            map.put(MetheaConstant.JSON_MESSAGE, "Access token generated!!!");
            map.put(MetheaConstant.JSON_STATUS, 200);
        } catch (Exception ex) {
            log.error("=========> Generate access token from user error: ", ex);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping(value = VERIFY_REFRESH_TOKEN, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> generateTokenFromRefreshToken(@RequestBody RefreshTokenPayload payload, HttpServletRequest req) {
        log.info("==========> start get access token form refresh token");
        Map<String, Object> map = new HashMap<>();

        try {
            Token token = authenticationService.generateTokenFromRefreshToken(payload, req);
            if (ObjectUtils.isEmpty(token)) {
                map.put(MetheaConstant.JSON_MESSAGE, "Invalid refresh token!!");
                map.put(MetheaConstant.JSON_STATUS, 400);
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
            map.put("token", token);
            map.put(MetheaConstant.JSON_MESSAGE, "Access token generated!!!");
            map.put(MetheaConstant.JSON_STATUS, 200);
        } catch (Exception ex) {
            log.error("=========> Generate access token from refresh token error: ", ex);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping(value = REVOKE_ACCESS_TOKEN_URL, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> revokeAccessToken(@RequestBody RevokeTokenPayload payload,
                                                                 HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        try {
            authenticationService.revokeAccessToken(payload, request);
            map.put(MetheaConstant.JSON_MESSAGE, "Access token revoked!!!");
            map.put(MetheaConstant.JSON_STATUS, 200);
        } catch (Exception ex) {
            log.error("=========> revokeAccessToken error: ", ex);
            map.put(MetheaConstant.JSON_MESSAGE, "Failed to revoke access token!!");
            map.put(MetheaConstant.JSON_STATUS, 400);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
