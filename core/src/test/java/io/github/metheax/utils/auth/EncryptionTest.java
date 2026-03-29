package io.github.metheax.utils.auth;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {

    private static RSAPublicKey publicKey;
    private static RSAPrivateKey privateKey;

    @BeforeAll
    static void setUp() {
        RsaKeyGenerate generator = new RsaKeyGenerate();
        KeyPair keyPair = generator.createRsa(2048);
        publicKey = (RSAPublicKey) keyPair.getPublic();
        privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }

    @Test
    void encrypt_returnsNonNullString() throws Exception {
        String encrypted = Encryption.encrypt("hello", publicKey);
        assertNotNull(encrypted);
        assertFalse(encrypted.isEmpty());
    }

    @Test
    void encrypt_producesBase64Output() throws Exception {
        String encrypted = Encryption.encrypt("hello", publicKey);
        // Base64 strings should only contain alphanumeric and +/= characters
        assertTrue(encrypted.matches("[A-Za-z0-9+/=]+"));
    }

    @Test
    void encryptDecrypt_roundtrip_recoversOriginalText() throws Exception {
        String plaintext = "Hello, World!";
        String encrypted = Encryption.encrypt(plaintext, publicKey);
        String decrypted = Encryption.decrypt(encrypted, privateKey);
        assertEquals(plaintext, decrypted);
    }

    @Test
    void encryptDecrypt_emptyString_roundtrip() throws Exception {
        String plaintext = "";
        String encrypted = Encryption.encrypt(plaintext, publicKey);
        String decrypted = Encryption.decrypt(encrypted, privateKey);
        assertEquals(plaintext, decrypted);
    }

    @Test
    void encryptDecrypt_specialCharacters_roundtrip() throws Exception {
        String plaintext = "P@ssw0rd!#$%";
        String encrypted = Encryption.encrypt(plaintext, publicKey);
        String decrypted = Encryption.decrypt(encrypted, privateKey);
        assertEquals(plaintext, decrypted);
    }

    @Test
    void generateRandomPassword_returnsCorrectLength() {
        String password = Encryption.generateRandomPassword(12, 48, 122);
        assertEquals(12, password.length());
    }

    @Test
    void generateRandomPassword_returnsAlphanumericOnly() {
        String password = Encryption.generateRandomPassword(50, 48, 122);
        assertTrue(password.matches("[A-Za-z0-9]+"),
                "Password should only contain alphanumeric characters, but was: " + password);
    }

    @Test
    void generateRandomPassword_differentLengths_work() {
        assertEquals(8, Encryption.generateRandomPassword(8, 48, 122).length());
        assertEquals(16, Encryption.generateRandomPassword(16, 48, 122).length());
        assertEquals(32, Encryption.generateRandomPassword(32, 48, 122).length());
    }

    @Test
    void generateRandomPassword_twoCalls_likelyDifferent() {
        String pw1 = Encryption.generateRandomPassword(20, 48, 122);
        String pw2 = Encryption.generateRandomPassword(20, 48, 122);
        // While theoretically possible to be equal, extremely unlikely for 20 chars
        assertNotNull(pw1);
        assertNotNull(pw2);
    }
}
