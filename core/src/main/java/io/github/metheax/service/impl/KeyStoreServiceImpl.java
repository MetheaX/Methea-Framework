package io.github.metheax.service.impl;

import io.github.metheax.exception.CertificateNotFoundException;
import io.github.metheax.exception.KeyPairException;
import io.github.metheax.service.KeyStoreService;
import io.github.metheax.utils.auth.CryptoUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

/**
 * Author: Kuylim TITH
 * Date: 6/5/2023
 */
@Service
@RequiredArgsConstructor
public class KeyStoreServiceImpl implements KeyStoreService {

    @Value("${keystore-token-file}")
    private String tokenKeystoreFile;
    @Value("${keystore-token-password}")
    private String tokenKeystorePassword;
    @Value("${keystore-token-alias}")
    private String tokenKeystoreAlias;
    @Value("${keystore-key-token-password}")
    private String tokenKeystoreKeyPassword;

    @Value("${keystore-refresh-token-file}")
    private String refreshTokenKeystoreFile;
    @Value("${keystore-refresh-token-password}")
    private String refreshTokenKeystorePassword;
    @Value("${keystore-refresh-token-alias}")
    private String refreshTokenKeystoreAlias;
    @Value("${keystore-key-refresh-token-password}")
    private String refreshTokenKeyPassword;

    private final ResourceLoader resourceLoader;

    @Override
    public KeyPair loadTokenKeyPair() {
        return loadKeyPair(tokenKeystoreFile, tokenKeystoreKeyPassword, tokenKeystorePassword, tokenKeystoreAlias);
    }

    @Override
    public KeyPair loadRefreshTokenKeyPair() {
        return loadKeyPair(refreshTokenKeystoreFile, refreshTokenKeyPassword, refreshTokenKeystorePassword, refreshTokenKeystoreAlias);
    }

    private KeyPair loadKeyPair(String resourceFile, String keyPassword, String keystorePassword, String alias) {
        PrivateKey privateKey = null;
        KeyStore keyStore = null;
        try {
            File tempFile = File.createTempFile("keystore", ".pfx");
            FileUtils.copyInputStreamToFile(resourceLoader.getResource(resourceFile).getInputStream(), tempFile);
            keyStore = CryptoUtil.loadPkcs12KeyStore(tempFile, keyPassword.toCharArray());

            privateKey = (PrivateKey) keyStore.getKey(alias, keystorePassword.toCharArray());
        } catch (Exception ex) {
            throw new KeyPairException("Could not load internal key!");
        }

        if (ObjectUtils.isEmpty(privateKey)) {
            throw new KeyPairException("Internal key not found!");
        }
        Certificate[] certificates = null;
        try {
            certificates = keyStore.getCertificateChain(alias);
        } catch (Exception ex) {
            throw new KeyPairException("Failed to load certificate!");
        }
        if (certificates == null || certificates.length < 1 || !(certificates[0] instanceof X509Certificate)) {
            throw new CertificateNotFoundException(resourceFile);
        }
        return new KeyPair(certificates[0].getPublicKey(), privateKey);
    }
}
