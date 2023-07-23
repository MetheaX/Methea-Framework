package io.github.metheax.domain.entity;

import jakarta.persistence.*;

/**
 * Author : Kuylim Tith
 * Date : 28/06/2020
 */
@Entity
@Table(name = "core_menu")
public class TMenu extends BaseEntity<TMenu> {

    private static final long serialVersionUID = 8047293537806520907L;

    @Id
    @Column(name = "menu_id")
    private String id;
    @Column(name = "parent_menu")
    private String parentId;
    @Column(name = "menu_label", nullable = false)
    private String menuLabel;
    @Column(name = "menu_icon", nullable = false)
    private String menuIcon;
    @ManyToOne
    @JoinColumn(name = "resource_id")
    private TResource resource;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private TGroup group;
    @Column(name = "index")
    private Integer index;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuLabel() {
        return menuLabel;
    }

    public void setMenuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public TResource getResource() {
        return resource;
    }

    public void setResource(TResource resource) {
        this.resource = resource;
    }

    public TGroup getGroup() {
        return group;
    }

    public void setGroup(TGroup group) {
        this.group = group;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
