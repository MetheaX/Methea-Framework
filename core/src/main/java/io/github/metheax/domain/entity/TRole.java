package io.github.metheax.domain.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Author : Kuylim Tith
 * Date : 21/08/2019
 */
@Entity
@Table(name = "core_role")
public class TRole extends BaseEntity<TRole> {
    private static final long serialVersionUID = 707732334293126239L;
    @Id
    @Column(name = "role_id", length = 36)
    private String id;
    @Column(name = "role_code", nullable = false, unique = true, length = 18)
    private String code;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "name_oth")
    private String nameOth;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TPermission> permissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOth() {
        return nameOth;
    }

    public void setNameOth(String nameOth) {
        this.nameOth = nameOth;
    }

    public List<TPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<TPermission> permissions) {
        this.permissions = permissions;
    }
}
