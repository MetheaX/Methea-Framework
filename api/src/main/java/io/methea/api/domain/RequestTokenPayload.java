package io.methea.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.methea.domain.common.binder.BaseBinder;

/**
 * Author : DKSilverX
 * Date : 08/08/2020
 */
public class RequestTokenPayload extends BaseBinder<RequestTokenPayload> {

    private static final long serialVersionUID = -3022490546117039346L;
    private String clientId;
    private String clientSecret;

    @JsonProperty("client_id")
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @JsonProperty("client_secret")
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
