package io.github.metheax.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;

/**
 * Author : Kuylim Tith
 * Date : 08/08/2020
 */
public class Token implements Serializable {
    @Serial
    private static final long serialVersionUID = 9137560474060234350L;

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private String expiredIn;

    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonProperty("refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @JsonProperty("token_type")
    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @JsonProperty("expired_in")
    public String getExpiredIn() {
        return expiredIn;
    }

    public void setExpiredIn(String expiredIn) {
        this.expiredIn = expiredIn;
    }
}
