package io.github.metheax.service;

import java.security.KeyPair;

/**
 * Author: Kuylim TITH
 * Date: 6/5/2023
 */
public interface KeyStoreService {

    KeyPair loadTokenKeyPair();

    KeyPair loadRefreshTokenKeyPair();
}
