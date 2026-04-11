package com.metheax.sena.utils.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.security.KeyStore;

import static org.junit.jupiter.api.Assertions.*;

class CryptoUtilTest {

    @TempDir
    Path tempDir;

    private File createKeystore(String type, String password) throws Exception {
        KeyStore ks = KeyStore.getInstance(type);
        ks.load(null, null);
        File file = tempDir.resolve("test." + type.toLowerCase()).toFile();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ks.store(fos, password.toCharArray());
        }
        return file;
    }

    @Test
    void loadPkcs12KeyStore_validFileAndPassword_returnsKeyStore() throws Exception {
        File file = createKeystore("PKCS12", "testpassword");
        KeyStore ks = CryptoUtil.loadPkcs12KeyStore(file, "testpassword".toCharArray());
        assertNotNull(ks);
        assertEquals("PKCS12", ks.getType());
    }

    @Test
    void loadKeyStore_pkcs12Type_returnsKeyStore() throws Exception {
        File file = createKeystore("PKCS12", "mypassword");
        KeyStore ks = CryptoUtil.loadKeyStore("PKCS12", file, "mypassword".toCharArray());
        assertNotNull(ks);
        assertEquals("PKCS12", ks.getType());
    }

    @Test
    void loadKeyStore_jksType_returnsKeyStore() throws Exception {
        File file = createKeystore("JKS", "jkspass");
        KeyStore ks = CryptoUtil.loadKeyStore("JKS", file, "jkspass".toCharArray());
        assertNotNull(ks);
        assertEquals("JKS", ks.getType());
    }

    @Test
    void loadPkcs12KeyStore_nonExistentFile_throwsException() {
        File nonExistent = tempDir.resolve("missing.p12").toFile();
        assertThrows(Exception.class,
                () -> CryptoUtil.loadPkcs12KeyStore(nonExistent, "any".toCharArray()));
    }

    @Test
    void loadKeyStore_unsupportedType_throwsException() {
        File file = tempDir.resolve("dummy.ks").toFile();
        assertThrows(Exception.class,
                () -> CryptoUtil.loadKeyStore("INVALID_TYPE", file, "pw".toCharArray()));
    }
}
