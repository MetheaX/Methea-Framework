package io.github.metheax.utils.auth;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

public class CryptoUtil {

    private CryptoUtil() {}

    public static KeyStore loadPkcs12KeyStore(File file, char[] password) throws Exception{
        return loadKeyStore("PKCS12", file, password);
    }

    public static KeyStore loadKeyStore(String type, File file, char[] password) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(type);
        try(FileInputStream fis = new FileInputStream(file)) {
            keyStore.load(fis, password);
        }
        return keyStore;
    }
}
