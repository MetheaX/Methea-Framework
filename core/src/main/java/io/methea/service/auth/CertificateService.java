package io.methea.service.auth;

import io.methea.constant.MConstant;
import io.methea.domain.webservice.SystemCertificate;
import io.methea.domain.webservice.dto.CertificateBinder;
import io.methea.repository.webservice.SystemCertificateRepository;
import io.methea.utils.auth.RsaKeyGenerate;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 06/05/2020
 */
@Service
public class CertificateService {
    private final SystemCertificateRepository certificateRepository;

    @Inject
    public CertificateService(SystemCertificateRepository certificateRepository) throws Exception {
        this.certificateRepository = certificateRepository;
    }

    public SystemCertificate createCertificate(CertificateBinder binder) {
        SystemCertificate certificate = new SystemCertificate();
        certificate.setId(UUID.randomUUID().toString());
        certificate.setCode(binder.getCode());

        KeyPair pair = new RsaKeyGenerate().createRsa(MConstant.FOUR_KEY_SIZE);
        RSAPublicKey pubKey = (RSAPublicKey) pair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) pair.getPrivate();

        certificate.setPrivateKey(Base64.encodeBase64String(privateKey.getEncoded()));
        certificate.setPublicKey(Base64.encodeBase64String(pubKey.getEncoded()));
        certificate.setStatus(MConstant.ACTIVE_STATUS);

        certificateRepository.save(certificate);

        return certificate;
    }
}
