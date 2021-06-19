package io.github.metheax.api.service;

import io.github.metheax.api.domain.RefreshTokenPayload;
import io.github.metheax.api.domain.RequestTokenPayload;
import io.github.metheax.api.domain.RevokeTokenPayload;
import io.github.metheax.api.domain.Token;

import javax.servlet.http.HttpServletRequest;

/**
 * Author : Kuylim Tith
 * Date : 08/08/2020
 */
public interface AuthenticationService {
    Token generateTokenFromUser(RequestTokenPayload payload, HttpServletRequest request);

    Token generateTokenFromRefreshToken(RefreshTokenPayload payload, HttpServletRequest request);

    void revokeAccessToken(RevokeTokenPayload payload, HttpServletRequest request);

    boolean validateUserRevokedToken(String subject);
}
