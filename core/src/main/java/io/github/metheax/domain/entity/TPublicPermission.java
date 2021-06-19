package io.github.metheax.domain.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Author : Kuylim Tith
 * Date : 12/09/2020
 */
@Entity
@Table(name = "core_public_permission")
public class TPublicPermission extends BaseEntity<TPublicPermission> {

    private static final long serialVersionUID = 8696065132921614456L;

    @Id
    @Column(name = "public_perm_id", length = 36)
    private String id;
    @OneToOne
    @JoinColumn(name = "resource_id")
    private TResource resource;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "core_perm_allowed_method", joinColumns = @JoinColumn(name = "public_perm_id"))
    @Column(name = "http_method")
    private List<String> allowedMethod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TResource getResource() {
        return resource;
    }

    public void setResource(TResource resource) {
        this.resource = resource;
    }

    public List<String> getAllowedMethod() {
        return allowedMethod;
    }

    public void setAllowedMethod(List<String> allowedMethod) {
        this.allowedMethod = allowedMethod;
    }
}
