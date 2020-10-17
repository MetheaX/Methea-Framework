package io.methea.domain.configuration.permission.entity;

import io.methea.domain.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Author : DKSilverX
 * Date : 12/09/2020
 */
@Entity
@Table(name = "tbl_core_white_uri")
public class TWhiteURIPermission extends BaseEntity<TWhiteURIPermission> {

    private static final long serialVersionUID = 8696065132921614456L;

    @Id
    @Column(name = "white_uri_id")
    private String id;
    @Column(name = "uri_id", nullable = false, unique = true)
    private String uriId;
    @Column(name = "uri_name", nullable = false, unique = true)
    private String uriName;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tbl_core_allowed_method", joinColumns = @JoinColumn(name = "white_uri_id"))
    @Column(name = "http_method")
    private List<String> allowedMethod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getAllowedMethod() {
        return allowedMethod;
    }

    public void setAllowedMethod(List<String> allowedMethod) {
        this.allowedMethod = allowedMethod;
    }
}
