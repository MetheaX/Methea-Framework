package io.methea.api.service;

import io.methea.api.domain.RefreshTokenPayload;
import io.methea.api.domain.RequestTokenPayload;
import io.methea.api.domain.Token;

import javax.servlet.http.HttpServletRequest;

/**
 * Author : DKSilverX
 * Date : 08/08/2020
 */
public interface AuthenticationService {
    Token generateTokenFromUser(RequestTokenPayload payload, HttpServletRequest request);

    Token generateTokenFromRefreshToken(RefreshTokenPayload payload, HttpServletRequest request);
}
