package io.github.metheax.domain.binder;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : DKSilverX
 * Date : 03/05/2020
 */
public class ClientBinder extends BaseBinder<ClientBinder> {

    private static final long serialVersionUID = 1054575166018682165L;

    private String clientId = StringUtils.EMPTY;
    private List<String> apiBases = new ArrayList<>();

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

    public List<String> getApiBases() {
        return apiBases;
    }

    public void setApiBases(List<String> apiBases) {
        this.apiBases = apiBases;
    }
}
