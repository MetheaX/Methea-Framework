package io.github.metheax.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.metheax.domain.binder.BaseBinder;

public class RevokeTokenPayload extends BaseBinder<RevokeTokenPayload> {

    private static final long serialVersionUID = -4336352262912103574L;

    private String clientID;
    private String clientToken;

    @JsonProperty("client_id")
    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    @JsonProperty("client_token")
    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }
}
