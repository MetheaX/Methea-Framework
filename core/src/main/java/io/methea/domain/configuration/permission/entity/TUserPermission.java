package io.methea.domain.configuration.permission.entity;

import io.methea.domain.baseentity.BaseEntity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 9/4/2019
 */
@Entity
@Table(name = "tbl_core_user_permission")
public class TUserPermission extends BaseEntity<TUserPermission> {

    private static final long serialVersionUID = -1637342316864349272L;
    @Id
    @Column(name = "permission_id")
    private String id;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "role_user_id", nullable = false)
    private String roleUserId;
    @Column(name = "uri_name", nullable = false)
    private String uriName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if(StringUtils.isEmpty(id)){
            this.id = UUID.randomUUID().toString();
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleUserId() {
        return roleUserId;
    }

    public void setRoleUserId(String roleUserId) {
        this.roleUserId = roleUserId;
    }

    public String getUriName() {
        return uriName;
    }

    public void setUriName(String uriName) {
        this.uriName = uriName;
    }
}
