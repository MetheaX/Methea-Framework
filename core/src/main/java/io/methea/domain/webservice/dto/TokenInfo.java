package io.methea.domain.webservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.methea.domain.basebinder.BaseBinder;

/**
 * Author : DKSilverX
 * Date : 03/05/2020
 */
public class TokenInfo extends BaseBinder<TokenInfo> {
    private static final long serialVersionUID = -1214966833081523840L;

    private String accessToken;
    private String verifyCode;
    private String expiredIn;

    public TokenInfo() {
    }

    public TokenInfo(String accessToken, String verifyCode, String expiredIn) {
        this.accessToken = accessToken;
        this.verifyCode = verifyCode;
        this.expiredIn = expiredIn;
    }

    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonIgnore
    @JsonProperty("verify_code")
    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @JsonProperty("expired_in")
    public String getExpiredIn() {
        return expiredIn;
    }

    public void setExpiredIn(String expiredIn) {
        this.expiredIn = expiredIn;
    }
}
