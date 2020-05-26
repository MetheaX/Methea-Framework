package io.methea.controller.webservice.certificate;

import io.methea.constant.MConstant;
import io.methea.domain.webservice.SystemCertificate;
import io.methea.domain.webservice.dto.CertificateBinder;
import io.methea.service.auth.CertificateService;
import io.methea.utils.SystemUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping(value = {SystemCertificateController.ROOT_URL})
public class SystemCertificateController {
    static final String ROOT_URL = "/app/system-certificate";
    private static final String CERTIFICATE_TEMPLATE_PATH = "webservice/certificate/system-certificate";

    private final CertificateService certificateService;

    @Inject
    public SystemCertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @RequestMapping(value = StringUtils.EMPTY, method = RequestMethod.GET)
    public String viewSystemCertificate(Model model, HttpServletRequest request) {
        model.addAttribute(MConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        model.addAttribute("certificate", certificateService.getSystemCertificate());
        return CERTIFICATE_TEMPLATE_PATH;
    }

    @RequestMapping(value = "/re-generate", method = RequestMethod.GET)
    public String reGenerateCertificate(Model model, HttpServletRequest request) {
        CertificateBinder binder = new CertificateBinder();
        binder.setCode(MConstant.CERT_TYPE);
        model.addAttribute(MConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        model.addAttribute("certificate", certificateService.createOrUpdateCertificate(binder));
        return CERTIFICATE_TEMPLATE_PATH;
    }
}
