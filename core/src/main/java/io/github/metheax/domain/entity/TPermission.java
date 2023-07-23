package io.github.metheax.domain.entity;

import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.UUID;

/**
 * Author : Kuylim Tith
 * Date : 9/4/2019
 */
@Entity
@Table(name = "core_permission")
public class TPermission extends BaseEntity<TPermission> {

    private static final long serialVersionUID = -1637342316864349272L;
    @Id
    @Column(name = "permission_id", length = 36)
    private String id;
    @ManyToOne
    @JoinColumn(name = "resource_id")
    private TResource resource;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private TRole role;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "core_perm_allowed_method", joinColumns = @JoinColumn(name = "public_perm_id"))
    @Column(name = "http_method")
    private List<String> allowedMethod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if(StringUtils.isEmpty(id)){
            this.id = UUID.randomUUID().toString();
        }
    }

    public TResource getResource() {
        return resource;
    }

    public void setResource(TResource resource) {
        this.resource = resource;
    }

    public TRole getRole() {
        return role;
    }

    public void setRole(TRole role) {
        this.role = role;
    }

    public List<String> getAllowedMethod() {
        return allowedMethod;
    }

    public void setAllowedMethod(List<String> allowedMethod) {
        this.allowedMethod = allowedMethod;
    }
}
