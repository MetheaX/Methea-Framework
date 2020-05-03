package io.methea.domain.webservice.dto;

import io.methea.domain.basebinder.BaseBinder;

/**
 * Author : DKSilverX
 * Date : 03/05/2020
 */
public class ClientBinder extends BaseBinder<ClientBinder> {

    private static final long serialVersionUID = 1054575166018682165L;

    private String clientId;

    public ClientBinder() {
    }

    public ClientBinder(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
