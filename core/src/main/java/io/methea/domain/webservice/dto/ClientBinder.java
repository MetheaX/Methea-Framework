package io.methea.domain.webservice.dto;

import io.methea.domain.basebinder.BaseBinder;
import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 03/05/2020
 */
public class ClientBinder extends BaseBinder<ClientBinder> {

    private static final long serialVersionUID = 1054575166018682165L;

    private String clientId = StringUtils.EMPTY;
    private String verifyCode = StringUtils.EMPTY;

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

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
