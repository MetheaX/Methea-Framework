package io.methea.service.auth;

import io.methea.constant.MConstant;
import io.methea.domain.webservice.system.entity.SystemCertificate;
import io.methea.repository.webservice.system.SystemCertificateRepository;
import io.methea.utils.auth.RsaKeyGenerate;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 06/05/2020
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CertificateService {

    private static final Logger log = LoggerFactory.getLogger(CertificateService.class);

    private final SystemCertificateRepository certificateRepository;

    private static final List<String> codes;

    static {
        codes = new ArrayList<>();
        codes.add(MConstant.CERT_TYPE);
        codes.add(MConstant.CERT_TYPE_2);
    }

    @Inject
    public CertificateService(SystemCertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    public boolean createOrRenewCertificate() {
        try {
            List<SystemCertificate> certificates = certificateRepository.findSystemCertificateByCodeIn(codes);
            if (!CollectionUtils.isEmpty(certificates)) {
                certificateRepository.deleteAll();
                certificates.clear();
            } else certificates = new ArrayList<>();
            for (String s : codes) {
                KeyPair pair = new RsaKeyGenerate().createRsa(MConstant.FOUR_KEY_SIZE);
                SystemCertificate certificate = new SystemCertificate();
                certificate.setCode(s);
                certificate.setId(UUID.randomUUID().toString());
                RSAPublicKey pubKey = (RSAPublicKey) pair.getPublic();
                RSAPrivateKey privateKey = (RSAPrivateKey) pair.getPrivate();

                certificate.setPrivateKey(Base64.encodeBase64String(privateKey.getEncoded()));
                certificate.setPublicKey(Base64.encodeBase64String(pubKey.getEncoded()));
                certificate.setStatus(MConstant.ACTIVE_STATUS);
                certificates.add(certificate);
            }
            certificateRepository.saveAll(certificates);
            return true;
        } catch (Exception ex) {
            log.info("=========> createOrRenewCertificate error: ", ex);
            return false;
        }
    }

    public String getSystemCertificate() {
        List<SystemCertificate> certificates = certificateRepository.findSystemCertificateByCodeIn(codes);
        for (SystemCertificate o : certificates) {
            if (MConstant.INACTIVE_STATUS.equals(o.getStatus())) return MConstant.INACTIVE_STATUS;
        }
        return MConstant.ACTIVE_STATUS;
    }

    public boolean activateCertificate() {
        try {
            List<SystemCertificate> certificates = certificateRepository.findSystemCertificateByCodeIn(codes);
            for (SystemCertificate o : certificates) {
                o.setStatus(MConstant.ACTIVE_STATUS);
                certificateRepository.save(o);
            }
            return true;
        } catch (Exception ex) {
            log.info("=========> activateCertificate error: ", ex);
            return false;
        }
    }

    public boolean deactivateCertificate() {
        try {
            List<SystemCertificate> certificates = certificateRepository.findSystemCertificateByCodeIn(codes);
            for (SystemCertificate o : certificates) {
                o.setStatus(MConstant.INACTIVE_STATUS);
                certificateRepository.save(o);
            }
            return true;
        } catch (Exception ex) {
            log.info("=========> deactivateCertificate error: ", ex);
            return false;
        }
    }
}
