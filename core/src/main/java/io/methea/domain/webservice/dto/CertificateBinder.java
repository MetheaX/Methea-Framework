package io.methea.domain.webservice.dto;

import io.methea.domain.basebinder.BaseBinder;
import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 06/05/2020
 */
public class CertificateBinder extends BaseBinder<CertificateBinder> {
    private static final long serialVersionUID = 5023936229240885458L;

    private String code = StringUtils.EMPTY;
    private String privateKey = StringUtils.EMPTY;
    private String publicKey = StringUtils.EMPTY;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
