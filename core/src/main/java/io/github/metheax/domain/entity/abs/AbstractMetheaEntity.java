package io.github.metheax.domain.entity.abs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Transient;

import java.io.Serial;
import java.io.Serializable;

/**
 * Author : Kuylim Tith
 * Date : 18/04/2020
 */
public abstract class AbstractMetheaEntity<E> implements Serializable {
    @Serial
    private static final long serialVersionUID = -7630748772877363263L;

    @Transient
    @JsonIgnore
    private boolean isCreate;
    @Transient
    @JsonIgnore
    private boolean isUpdate;
    @Transient
    @JsonIgnore
    private boolean isActivate;
    @Transient
    @JsonIgnore
    private boolean isDeactivate;

    public boolean isCreate() {
        return isCreate;
    }

    public void setCreate(boolean create) {
        isCreate = create;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    public boolean isActivate() {
        return isActivate;
    }

    public void setActivate(boolean activate) {
        isActivate = activate;
    }

    public boolean isDeactivate() {
        return isDeactivate;
    }

    public void setDeactivate(boolean deactivate) {
        isDeactivate = deactivate;
    }
}
