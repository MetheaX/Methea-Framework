package io.github.metheax.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionsTest {

    @Test
    void keyPairException_storesMessage() {
        KeyPairException ex = new KeyPairException("Could not load key!");
        assertEquals("Could not load key!", ex.getMessage());
        assertInstanceOf(RuntimeException.class, ex);
    }

    @Test
    void certificateNotFoundException_storesMessage() {
        CertificateNotFoundException ex = new CertificateNotFoundException("cert.pfx");
        assertEquals("cert.pfx", ex.getMessage());
        assertInstanceOf(RuntimeException.class, ex);
    }

    @Test
    void invalidClientSecretException_storesMessage() {
        InvalidClientSecretException ex = new InvalidClientSecretException("Invalid secret");
        assertEquals("Invalid secret", ex.getMessage());
        assertInstanceOf(RuntimeException.class, ex);
    }
}
