package io.methea.domain.webservice.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.methea.domain.common.binder.BaseBinder;

/**
 * Author : DKSilverX
 * Date : 03/05/2020
 */
public class ClientAuthentication extends BaseBinder<ClientAuthentication> {
    private static final long serialVersionUID = 9222969564854781982L;

    private String clientId;
    private String clientSecret;
    private String verifyCode;

    public ClientAuthentication(){}

    public ClientAuthentication(String clientId, String clientSecret, String verifyCode) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.verifyCode = verifyCode;
    }

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

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
