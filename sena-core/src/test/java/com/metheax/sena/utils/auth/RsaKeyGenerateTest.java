package com.metheax.sena.utils.auth;

import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RsaKeyGenerateTest {

    @Test
    void createRsa_2048_returnsNonNullKeyPair() {
        RsaKeyGenerate generator = new RsaKeyGenerate();
        KeyPair keyPair = generator.createRsa(2048);
        assertNotNull(keyPair);
    }

    @Test
    void createRsa_2048_publicKeyNotNull() {
        RsaKeyGenerate generator = new RsaKeyGenerate();
        KeyPair keyPair = generator.createRsa(2048);
        assertNotNull(keyPair.getPublic());
    }

    @Test
    void createRsa_2048_privateKeyNotNull() {
        RsaKeyGenerate generator = new RsaKeyGenerate();
        KeyPair keyPair = generator.createRsa(2048);
        assertNotNull(keyPair.getPrivate());
    }

    @Test
    void createRsa_publicKeyAlgorithmIsRSA() {
        RsaKeyGenerate generator = new RsaKeyGenerate();
        KeyPair keyPair = generator.createRsa(2048);
        assertEquals("RSA", keyPair.getPublic().getAlgorithm());
    }

    @Test
    void createRsa_privateKeyAlgorithmIsRSA() {
        RsaKeyGenerate generator = new RsaKeyGenerate();
        KeyPair keyPair = generator.createRsa(2048);
        assertEquals("RSA", keyPair.getPrivate().getAlgorithm());
    }

    @Test
    void createRsa_twoCalls_produceDifferentKeyPairs() {
        RsaKeyGenerate generator = new RsaKeyGenerate();
        KeyPair kp1 = generator.createRsa(2048);
        KeyPair kp2 = generator.createRsa(2048);
        // Different key pairs should have different encoded bytes
        assertFalse(Arrays.equals(kp1.getPublic().getEncoded(), kp2.getPublic().getEncoded()),
                "Two generated key pairs should be distinct");
    }

    @Test
    void createRsa_3072_returnsNonNullKeyPair() {
        RsaKeyGenerate generator = new RsaKeyGenerate();
        KeyPair keyPair = generator.createRsa(3072);
        assertNotNull(keyPair);
        assertEquals("RSA", keyPair.getPublic().getAlgorithm());
    }
}
