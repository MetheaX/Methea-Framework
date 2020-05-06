package io.methea.domain.webservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.methea.domain.basebinder.BaseBinder;

/**
 * Author : DKSilverX
 * Date : 03/05/2020
 */
public class TokenInfo extends BaseBinder<TokenInfo> {
    private static final long serialVersionUID = -1214966833081523840L;

    private String accessToken;
    private String tokenType;
    private String expiredIn;

    public TokenInfo() {
    }

    public TokenInfo(String accessToken, String tokenType, String expiredIn) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiredIn = expiredIn;
    }

    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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
