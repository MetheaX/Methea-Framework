package io.methea.domain.configuration.uri.filter;

import io.methea.domain.basefilter.AbstractMetheaFilter;
import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 06/05/2020
 */
public class URIFilter extends AbstractMetheaFilter<URIFilter> {
    private static final long serialVersionUID = 3303572474729221553L;

    private String uriName;
    private String status;

    public URIFilter() {
        this.uriName = StringUtils.EMPTY;
        this.status = StringUtils.EMPTY;
    }

    public URIFilter(String uriName, String status) {
        this.uriName = uriName;
        this.status = status;
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
