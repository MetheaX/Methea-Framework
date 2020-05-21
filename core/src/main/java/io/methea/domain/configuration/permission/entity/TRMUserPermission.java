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
 * Date : 19/05/2020
 */
@Entity
@Table(name = "tbl_core_rm_user_perm")
public class TRMUserPermission extends BaseEntity<TRMUserPermission> {

    private static final long serialVersionUID = 7935790936746765794L;

    @Id
    @Column(name = "permission_id")
    private String id;
    @Column(name = "user_login_id", nullable = false)
    private String userLoginId;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "role_id", nullable = false)
    private String roleId;
    @Column(name = "role_name", nullable = false)
    private String roleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if(StringUtils.isEmpty(id)){
            this.id = UUID.randomUUID().toString();
        }
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
