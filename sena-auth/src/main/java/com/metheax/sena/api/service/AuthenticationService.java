package com.metheax.sena.api.service;

import com.metheax.sena.api.domain.RefreshTokenPayload;
import com.metheax.sena.api.domain.RequestTokenPayload;
import com.metheax.sena.api.domain.RevokeTokenPayload;
import com.metheax.sena.api.domain.Token;
import jakarta.servlet.http.HttpServletRequest;

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
