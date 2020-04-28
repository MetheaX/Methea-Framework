package io.methea.controller.auth;

import io.methea.domain.configuration.user.dto.UserLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

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

    @Inject
    public MRestAuthController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = GET_ACCESS_TOKEN_URL)
    public ResponseEntity<Map<String, Object>> generateToken(@RequestBody UserLogin user) {

        Map<String, Object> map = new HashMap<>();
        String baseURL = "http://localhost:8080/login";
        HttpEntity<UserLogin> request = new HttpEntity<>(user);
        try {
            ResponseEntity<String> result = restTemplate.postForEntity(baseURL, request, String.class);
            map.put("user", result);
        } catch (Exception ex) {
            log.error(">>>>> Generate access token error: ", ex);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
