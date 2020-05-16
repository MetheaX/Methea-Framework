package io.methea.domain.configuration.uri.entity;

import io.methea.domain.baseentity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author : DKSilverX
 * Date : 01/02/2020
 */
@Entity
@Table(name = "tbl_core_role_uri")
public class TRoleURI extends BaseEntity<TRoleURI> {
    private static final long serialVersionUID = -6211213996483237997L;

    @Id
    @Column(name = "role_uri_id")
    private String id;
    private String roleId;
    private String uriId;
    private String uriName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUriId() {
        return uriId;
    }

    public void setUriId(String uriId) {
        this.uriId = uriId;
    }

    public String getUriName() {
        return uriName;
    }

    public void setUriName(String uriName) {
        this.uriName = uriName;
    }
}
