package io.github.metheax.domain.filter;

import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 17/10/2020
 */
public class WhiteURIPermissionFilter extends BaseFilter<WhiteURIPermissionFilter> {
    private static final long serialVersionUID = 767348908992719541L;

    private String uriName;
    private String status;

    public WhiteURIPermissionFilter() {
        this.uriName = StringUtils.EMPTY;
        this.status = StringUtils.EMPTY;
    }

    public WhiteURIPermissionFilter(String uriName, String status) {
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
