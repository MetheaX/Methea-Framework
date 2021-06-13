package io.github.metheax.utils.auth;

import io.github.metheax.constant.MetheaConstant;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Author: Kuylim TITH
 * Date: 6/13/2021
 */
@Component
public class MetheaPasswordEncoder implements PasswordEncoder {

    private final Argon2PasswordEncoder encoder;

    public MetheaPasswordEncoder(Argon2PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String encode(CharSequence charSequence) {
        return encoder.encode(charSequence).split(MetheaConstant.ARGON_PREFIX_SPLIT)[1];
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encoder.matches(charSequence, s);
    }
}
