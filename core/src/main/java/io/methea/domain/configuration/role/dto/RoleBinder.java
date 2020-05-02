package io.methea.domain.configuration.role.dto;

import io.methea.domain.basebinder.BaseBinder;
import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 02/05/2020
 */
public class RoleBinder extends BaseBinder<RoleBinder> {
    private static final long serialVersionUID = -8570219884504265055L;

    private String id = StringUtils.EMPTY;
    private String name = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
