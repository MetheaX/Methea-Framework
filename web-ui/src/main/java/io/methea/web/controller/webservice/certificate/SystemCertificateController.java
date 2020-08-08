package io.methea.web.controller.webservice.certificate;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.domain.webservice.system.dto.CertificateBinder;
import io.methea.service.auth.CertificateService;
import io.methea.utils.PrincipalUtils;
import io.methea.utils.SystemUtils;
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
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = StringUtils.EMPTY, method = RequestMethod.GET)
    public String viewSystemCertificate(Model model, HttpServletRequest request) {
        model.addAttribute(MConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        model.addAttribute("certificateStatus", certificateService.getSystemCertificate().getStatus());
        model.addAttribute(MConstant.CORE_MENU, MCache.CACHE_MENU.get(PrincipalUtils.getLoginGroupId(request)));
        return CERTIFICATE_TEMPLATE_PATH;
    }

    @RequestMapping(value = RE_GENERATE_URL, method = RequestMethod.GET)
    public ModelAndView reGenerateCertificate(Model model, HttpServletRequest request) {
        CertificateBinder binder = new CertificateBinder();
        binder.setCode(MConstant.CERT_TYPE);
        certificateService.createOrUpdateCertificate(binder);
        model.addAttribute(MConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        model.addAttribute(MConstant.CORE_MENU, MCache.CACHE_MENU.get(PrincipalUtils.getLoginGroupId(request)));
        return new ModelAndView("redirect:".concat(ROOT_URL));
    }

    @ResponseBody
    @PostMapping(value = DEACTIVATE_URL)
    public ResponseEntity<Map<String, Object>> deactivateCertificate(@RequestBody Map<String, Object> payload) {
        Map<String, Object> map = new HashMap<>();
        map.put(MConstant.JSON_STATUS, 500);
        map.put(MConstant.JSON_MESSAGE, "Failed to deactivate certificate!!!");
        if (!ObjectUtils.isEmpty(certificateService.deactivateCertificate())) {
            map.put(MConstant.JSON_STATUS, 200);
            map.put(MConstant.JSON_MESSAGE, "Deactivate certificate success!!!");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = ACTIVATE_URL)
    public ResponseEntity<Map<String, Object>> activateCertificate(@RequestBody Map<String, Object> payload) {
        Map<String, Object> map = new HashMap<>();
        map.put(MConstant.JSON_STATUS, 500);
        map.put(MConstant.JSON_MESSAGE, "Failed to activate certificate!!!");
        if (!ObjectUtils.isEmpty(certificateService.activateCertificate())) {
            map.put(MConstant.JSON_STATUS, 200);
            map.put(MConstant.JSON_MESSAGE, "Activate certificate success!!!");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
