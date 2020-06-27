package io.methea.domain.configuration.uri.dto;

import io.methea.domain.common.binder.BaseBinder;
import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 06/05/2020
 */
public class URIBinder extends BaseBinder<URIBinder> {
    private static final long serialVersionUID = 8594041741982481921L;

    private String id = StringUtils.EMPTY;
    private String uriName = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUriName() {
        return uriName;
    }

    public void setUriName(String uriName) {
        this.uriName = uriName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
