package io.github.metheax.utils.auth;

import io.github.metheax.constant.MetheaConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class MetheaPasswordEncoderTest {

    private MetheaPasswordEncoder encoder;
    private Argon2PasswordEncoder argon2;

    @BeforeEach
    void setUp() {
        argon2 = new Argon2PasswordEncoder(
                MetheaConstant.SALT_LENGTH,
                MetheaConstant.HASH_LENGTH,
                MetheaConstant.PARALLELISM,
                MetheaConstant.MEMORY,
                MetheaConstant.ITERATIONS);
        encoder = new MetheaPasswordEncoder(argon2);
    }

    @Test
    void encode_returnsNonNullResult() {
        String encoded = encoder.encode("password");
        assertNotNull(encoded);
        assertFalse(encoded.isEmpty());
    }

    @Test
    void encode_stripsArgon2Prefix() {
        // MetheaPasswordEncoder.encode strips the "$argon2id$v=19$m=...,t=...,p=...$" prefix
        String encoded = encoder.encode("password");
        assertFalse(encoded.startsWith("$argon2id"),
                "Encoded value should not contain the argon2id prefix");
    }

    @Test
    void encode_twoCalls_produceDifferentHashes() {
        // Argon2 uses random salt per call, so same password should hash differently
        String encoded1 = encoder.encode("password");
        String encoded2 = encoder.encode("password");
        assertNotEquals(encoded1, encoded2,
                "Two encodings of the same password should produce different hashes (random salt)");
    }

    @Test
    void matches_correctPassword_returnsTrue() {
        // User.getPassword() prepends ARGON_PREFIX to what's stored in DB
        // So matches receives the full argon2 hash
        String stripped = encoder.encode("mypassword");
        String fullHash = MetheaConstant.ARGON_PREFIX + stripped;

        assertTrue(encoder.matches("mypassword", fullHash));
    }

    @Test
    void matches_wrongPassword_returnsFalse() {
        String stripped = encoder.encode("mypassword");
        String fullHash = MetheaConstant.ARGON_PREFIX + stripped;

        assertFalse(encoder.matches("wrongpassword", fullHash));
    }

    @Test
    void matches_delegatesToUnderlyingArgon2Encoder() {
        // Verify matches delegates correctly by using the underlying encoder directly
        String fullHashFromUnderlying = argon2.encode("testpass");
        assertTrue(encoder.matches("testpass", fullHashFromUnderlying));
        assertFalse(encoder.matches("wrongpass", fullHashFromUnderlying));
    }
}
