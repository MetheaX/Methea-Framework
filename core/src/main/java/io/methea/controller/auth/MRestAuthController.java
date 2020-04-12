package io.methea.controller.auth;

import io.methea.domain.configuration.user.dto.UserLogin;
import io.methea.domain.configuration.user.entity.TUser;
import io.methea.repository.configuration.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 8/24/2019
 */
@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MRestAuthController {

    private static Logger log = LoggerFactory.getLogger(MRestAuthController.class);

    private static final String ACTIVATE_SYS_URL = "/activate-sys";
    private static final String GET_ACCESS_TOKEN_URL = "/auth/token";

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Inject
    public MRestAuthController(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = ACTIVATE_SYS_URL)
    public String activateSys() {
        TUser user;

        user = userRepository.findByUsername("admin");

        if (!ObjectUtils.isEmpty(user)) {
            user = new TUser();
            user.setId(UUID.randomUUID().toString());
            user.setUsername("admin");
            user.setPassword("admin");
            user.setStatus("A");
            userRepository.save(user);
            return "Your system activated. Admin username: admin, Password: admin";
        }
        user.setPassword("admin");
        user.setStatus("A");
        userRepository.save(user);
        return "Your system activated once, we reset to default password: admin";
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
