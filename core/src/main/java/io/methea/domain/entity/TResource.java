package io.methea.domain.entity;

import javax.persistence.*;

/**
 * Author : DKSilverX
 * Date : 15/09/2019
 */
@Entity
@Table(name = "core_resource")
public class TResource extends BaseEntity<TResource> {

    private static final long serialVersionUID = -8160909806511195577L;
    @Id
    @Column(name = "resource_id", length = 36)
    private String id;
    @Column(name = "resource_name", nullable = false)
    private String resourceName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
