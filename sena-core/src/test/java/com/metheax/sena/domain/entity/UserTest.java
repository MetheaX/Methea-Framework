package com.metheax.sena.domain.entity;

import com.metheax.sena.constant.MetheaConstant;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getPassword_prependsArgonPrefix() {
        User user = new User();
        // Directly set the raw (post-encode) password field via setPassword
        user.setPassword("plaintextPassword");
        // getPassword() should prepend the ARGON_PREFIX
        assertTrue(user.getPassword().startsWith(MetheaConstant.ARGON_PREFIX),
                "getPassword() must prepend the Argon2 prefix");
    }

    @Test
    void setPassword_encodesWithArgon2AndStripsPrefix() {
        User user = new User();
        user.setPassword("mySecret123");

        String stored = user.getPassword(); // includes prefix
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(
                MetheaConstant.SALT_LENGTH, MetheaConstant.HASH_LENGTH,
                MetheaConstant.PARALLELISM, MetheaConstant.MEMORY, MetheaConstant.ITERATIONS);

        assertTrue(encoder.matches("mySecret123", stored),
                "Stored Argon2 hash must match the original plain-text password");
    }

    @Test
    void setPassword_differentPasswords_produceDifferentHashes() {
        User u1 = new User();
        User u2 = new User();
        u1.setPassword("password1");
        u2.setPassword("password2");
        assertNotEquals(u1.getPassword(), u2.getPassword());
    }

    @Test
    void noArgsConstructor_fieldsAreNull() {
        User user = new User();
        assertNull(user.getUsername());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
    }
}
