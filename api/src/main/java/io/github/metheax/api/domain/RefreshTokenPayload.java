package io.github.metheax.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.metheax.domain.binder.BaseBinder;

/**
 * Author : Kuylim Tith
 * Date : 08/08/2020
 */
public class RefreshTokenPayload extends BaseBinder<RefreshTokenPayload> {

    private static final long serialVersionUID = -8937218850725025841L;

    private String refreshToken;

    @JsonProperty("refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
