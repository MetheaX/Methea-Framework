package io.methea.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.methea.domain.common.binder.BaseBinder;

public class RevokeTokenPayload extends BaseBinder<RevokeTokenPayload> {

    private static final long serialVersionUID = -4336352262912103574L;

    private String clientSecret;
    private String clientToken;

    @JsonProperty("client_id")
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @JsonProperty("client_token")
    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }
}
