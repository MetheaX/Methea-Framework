package io.github.metheax.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;

/**
 * Author : Kuylim Tith
 * Date : 08/08/2020
 */
public class RequestTokenPayload implements Serializable {

    @Serial
    private static final long serialVersionUID = -3022490546117039346L;
    private String username;
    private String password;

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
