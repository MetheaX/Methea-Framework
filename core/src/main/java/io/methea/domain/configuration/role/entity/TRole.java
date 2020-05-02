package io.methea.domain.configuration.role.entity;

import io.methea.domain.baseentity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
@Entity
@Table(name = "tbl_core_role")
public class TRole extends BaseEntity<TRole> {
    private static final long serialVersionUID = 707732334293126239L;
    @Id
    @Column(name = "role_id")
    private String id;
    private String name;

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
}
