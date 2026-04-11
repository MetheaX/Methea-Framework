package com.metheax.sena.service.impl;

import com.metheax.sena.exception.KeyPairException;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KeyStoreServiceImplTest {

    @Mock ResourceLoader resourceLoader;
    @Mock Resource resource;

    private KeyStoreServiceImpl service;

    private static byte[] keystoreBytes;
    private static final String ALIAS = "testalias";
    private static final String KS_PASSWORD = "kspassword";
    private static final String KEY_PASSWORD = "keypassword";

    @BeforeAll
    static void buildTestKeystore() throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA", "BC");
        gen.initialize(2048);
        KeyPair kp = gen.generateKeyPair();

        // Build a self-signed X.509 certificate
        X500Name subject = new X500Name("CN=Test,O=Test,C=KH");
        Date notBefore = new Date(System.currentTimeMillis() - 1000);
        Date notAfter  = new Date(System.currentTimeMillis() + 365L * 24 * 3600 * 1000);

        X509v3CertificateBuilder certBuilder = new JcaX509v3CertificateBuilder(
                subject, BigInteger.ONE, notBefore, notAfter, subject, kp.getPublic());

        ContentSigner signer = new JcaContentSignerBuilder("SHA256WithRSA")
                .setProvider("BC").build(kp.getPrivate());

        X509Certificate cert = new JcaX509CertificateConverter()
                .setProvider("BC").getCertificate(certBuilder.build(signer));

        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(null, null);
        ks.setKeyEntry(ALIAS, kp.getPrivate(), KEY_PASSWORD.toCharArray(), new Certificate[]{cert});

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ks.store(bos, KS_PASSWORD.toCharArray());
        keystoreBytes = bos.toByteArray();
    }

    @BeforeEach
    void setUp() throws Exception {
        service = new KeyStoreServiceImpl(resourceLoader);
        // Inject @Value fields
        ReflectionTestUtils.setField(service, "tokenKeystoreFile",    "classpath:token.pfx");
        ReflectionTestUtils.setField(service, "tokenKeystorePassword", KEY_PASSWORD);
        ReflectionTestUtils.setField(service, "tokenKeystoreAlias",    ALIAS);
        ReflectionTestUtils.setField(service, "tokenKeystoreKeyPassword", KS_PASSWORD);

        ReflectionTestUtils.setField(service, "refreshTokenKeystoreFile",    "classpath:refresh.pfx");
        ReflectionTestUtils.setField(service, "refreshTokenKeystorePassword", KEY_PASSWORD);
        ReflectionTestUtils.setField(service, "refreshTokenKeystoreAlias",    ALIAS);
        ReflectionTestUtils.setField(service, "refreshTokenKeyPassword", KS_PASSWORD);
    }

    private void mockResourceLoaderWithKeystore() throws Exception {
        when(resourceLoader.getResource(anyString())).thenReturn(resource);
        when(resource.getInputStream())
                .thenReturn(new ByteArrayInputStream(keystoreBytes))
                .thenReturn(new ByteArrayInputStream(keystoreBytes));
    }

    @Test
    void loadTokenKeyPair_validKeystore_returnsKeyPair() throws Exception {
        mockResourceLoaderWithKeystore();

        KeyPair kp = service.loadTokenKeyPair();

        assertNotNull(kp);
        assertNotNull(kp.getPublic());
        assertNotNull(kp.getPrivate());
        assertEquals("RSA", kp.getPublic().getAlgorithm());
    }

    @Test
    void loadRefreshTokenKeyPair_validKeystore_returnsKeyPair() throws Exception {
        mockResourceLoaderWithKeystore();

        KeyPair kp = service.loadRefreshTokenKeyPair();

        assertNotNull(kp);
        assertNotNull(kp.getPublic());
        assertNotNull(kp.getPrivate());
    }

    @Test
    void loadTokenKeyPair_resourceThrows_throwsKeyPairException() throws Exception {
        when(resourceLoader.getResource(anyString())).thenReturn(resource);
        when(resource.getInputStream()).thenThrow(new java.io.IOException("File not found"));

        assertThrows(KeyPairException.class, () -> service.loadTokenKeyPair());
    }

    @Test
    void loadTokenKeyPair_wrongAlias_throwsKeyPairExceptionForMissingKey() throws Exception {
        ReflectionTestUtils.setField(service, "tokenKeystoreAlias", "nonexistent-alias");
        mockResourceLoaderWithKeystore();

        assertThrows(KeyPairException.class, () -> service.loadTokenKeyPair());
    }

    @Test
    void loadTokenKeyPair_wrongKeystorePassword_throwsKeyPairException() throws Exception {
        ReflectionTestUtils.setField(service, "tokenKeystorePassword", "wrongpassword");
        when(resourceLoader.getResource(anyString())).thenReturn(resource);
        when(resource.getInputStream()).thenReturn(new ByteArrayInputStream(keystoreBytes));

        // Wrong password used to load the key store entry; may throw KeyPairException
        assertThrows(KeyPairException.class, () -> service.loadTokenKeyPair());
    }
}
