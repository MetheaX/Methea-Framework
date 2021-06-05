package io.github.metheax.web.controller;

import io.github.metheax.cache.MetheaCache;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.service.CertificateService;
import io.github.metheax.utils.PrincipalUtils;
import io.github.metheax.utils.SystemUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping(value = {SystemCertificateController.ROOT_URL})
public class SystemCertificateController {
    static final String ROOT_URL = "/app/system-certificate";

    private static final String CERTIFICATE_TEMPLATE_PATH = "webservice/certificate/system-certificate";
    private static final String RE_GENERATE_URL = "/re-generate";
    private static final String DEACTIVATE_URL = "/rest/deactivate";
    private static final String ACTIVATE_URL = "/rest/activate";

    private final CertificateService certificateService;

    @Inject
    public SystemCertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping(value = StringUtils.EMPTY)
    public String viewSystemCertificate(Model model, HttpServletRequest request) {
        model.addAttribute(MetheaConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        model.addAttribute("certificateStatus", certificateService.getSystemCertificate());
        model.addAttribute(MetheaConstant.CORE_MENU, MetheaCache.CACHE_MENU.get(PrincipalUtils.getLoginGroupId(request)));
        return CERTIFICATE_TEMPLATE_PATH;
    }

    @ResponseBody
    @PostMapping(value = RE_GENERATE_URL)
    public ResponseEntity<Map<String, Object>> reGenerateCertificate(@RequestBody Map<String, Object> payload) {
        Map<String, Object> map = new HashMap<>();
        map.put(MetheaConstant.JSON_STATUS, 500);
        map.put(MetheaConstant.JSON_MESSAGE, "Failed to renew certificate!");
        if (!ObjectUtils.isEmpty(certificateService.createOrRenewCertificate())) {
            map.put(MetheaConstant.JSON_STATUS, 200);
            map.put(MetheaConstant.JSON_MESSAGE, "System certificate renewed!");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = DEACTIVATE_URL)
    public ResponseEntity<Map<String, Object>> deactivateCertificate(@RequestBody Map<String, Object> payload) {
        Map<String, Object> map = new HashMap<>();
        map.put(MetheaConstant.JSON_STATUS, 500);
        map.put(MetheaConstant.JSON_MESSAGE, "Failed to deactivate certificate!!!");
        if (certificateService.deactivateCertificate()) {
            map.put(MetheaConstant.JSON_STATUS, 200);
            map.put(MetheaConstant.JSON_MESSAGE, "Deactivate certificate success!!!");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = ACTIVATE_URL)
    public ResponseEntity<Map<String, Object>> activateCertificate(@RequestBody Map<String, Object> payload) {
        Map<String, Object> map = new HashMap<>();
        map.put(MetheaConstant.JSON_STATUS, 500);
        map.put(MetheaConstant.JSON_MESSAGE, "Failed to activate certificate!!!");
        if (certificateService.activateCertificate()) {
            map.put(MetheaConstant.JSON_STATUS, 200);
            map.put(MetheaConstant.JSON_MESSAGE, "Activate certificate success!!!");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
