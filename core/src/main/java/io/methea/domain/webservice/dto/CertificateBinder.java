package io.methea.domain.webservice.dto;

import io.methea.domain.basebinder.BaseBinder;

/**
 * Author : DKSilverX
 * Date : 06/05/2020
 */
public class CertificateBinder extends BaseBinder<CertificateBinder> {
    private static final long serialVersionUID = 5023936229240885458L;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
