package io.methea.api.auth;

import io.methea.api.domain.RefreshTokenPayload;
import io.methea.api.domain.RequestTokenPayload;
import io.methea.api.domain.RevokeTokenPayload;
import io.methea.api.domain.Token;
import io.methea.api.service.MetheaAuthenticationService;
import io.methea.constant.MConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 08/08/2020
 */
@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthenticationController {
    private static Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    private static final String UNAUTHORIZED_ACCESS_URL = "/unauthorized-access";
    private static final String GET_ACCESS_TOKEN_URL = "/auth/token";
    private static final String VERIFY_REFRESH_TOKEN = "/auth/refresh/token";
    private static final String REVOKE_ACCESS_TOKEN_URL = "/auth/token/revoke";

    private final MetheaAuthenticationService authenticationService;

    @Inject
    public AuthenticationController(MetheaAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping(value = UNAUTHORIZED_ACCESS_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> unauthorizedAccess() {
        Map<String, Object> map = new HashMap<>();
        map.put(MConstant.JSON_MESSAGE, "Unauthorized Access!!");
        map.put(MConstant.JSON_STATUS, 401);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping(value = GET_ACCESS_TOKEN_URL, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> generateTokenFromUser(@RequestBody RequestTokenPayload client, HttpServletRequest req) {
        Map<String, Object> map = new HashMap<>();
        try {
            Token token = authenticationService.generateTokenFromUser(client, req);

            if (ObjectUtils.isEmpty(token)) {
                map.put(MConstant.JSON_MESSAGE, "Invalid account!!");
                map.put(MConstant.JSON_STATUS, 400);
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
            map.put("token", token);
            map.put(MConstant.JSON_MESSAGE, "Access token generated!!!");
            map.put(MConstant.JSON_STATUS, 200);
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
                map.put(MConstant.JSON_MESSAGE, "Invalid refresh token!!");
                map.put(MConstant.JSON_STATUS, 400);
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
            map.put("token", token);
            map.put(MConstant.JSON_MESSAGE, "Access token generated!!!");
            map.put(MConstant.JSON_STATUS, 200);
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
            map.put(MConstant.JSON_MESSAGE, "Access token revoked!!!");
            map.put(MConstant.JSON_STATUS, 200);
        } catch (Exception ex) {
            log.error("=========> revokeAccessToken error: ", ex);
            map.put(MConstant.JSON_MESSAGE, "Failed to revoke access token!!");
            map.put(MConstant.JSON_STATUS, 400);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
