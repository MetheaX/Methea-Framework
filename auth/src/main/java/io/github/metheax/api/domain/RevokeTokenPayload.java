package io.github.metheax.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;

public class RevokeTokenPayload implements Serializable {

    @Serial
    private static final long serialVersionUID = -4336352262912103574L;

    private String username;
    private String token;

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
