package io.methea.domain.configuration.uri.entity;

import io.methea.domain.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * Author : DKSilverX
 * Date : 15/09/2019
 */
@Entity
@Table(name = "tbl_core_uri")
public class TMstURI extends BaseEntity<TMstURI> {

    private static final long serialVersionUID = -8160909806511195577L;
    @Id
    @Column(name = "uri_id")
    private String id;
    @Column(name = "uri_name", nullable = false)
    private String uriName;

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
}
