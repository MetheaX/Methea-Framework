package io.github.metheax.domain.filter;

import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 06/06/2020
 */
public class APIBaseFilter extends BaseFilter<APIBaseFilter> {
    private static final long serialVersionUID = 2719604740121239721L;

    private String apiUrl;
    private String status;

    public APIBaseFilter(){
        this.apiUrl = StringUtils.EMPTY;
        this.status = StringUtils.EMPTY;
    }

    public APIBaseFilter(String apiUrl, String status) {
        this.apiUrl = apiUrl;
        this.status = status;
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
