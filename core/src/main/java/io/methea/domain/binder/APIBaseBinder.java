package io.methea.domain.binder;

import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 06/06/2020
 */
public class APIBaseBinder extends BaseBinder<APIBaseBinder> {

    private static final long serialVersionUID = -7309702416404125435L;

    private String id = StringUtils.EMPTY;
    private String apiUrl = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
