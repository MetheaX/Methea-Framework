package io.methea.domain.configuration.role.filter;

import io.methea.domain.common.filter.BaseFilter;
import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 02/05/2020
 */
public class RoleFilter extends BaseFilter<RoleFilter> {
    private static final long serialVersionUID = -6705953405867512856L;

    private String name;
    private String status;

    public RoleFilter() {
        this.name = StringUtils.EMPTY;
        this.status = StringUtils.EMPTY;
    }

    public RoleFilter(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
