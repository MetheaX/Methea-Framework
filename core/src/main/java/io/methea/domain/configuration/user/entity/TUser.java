package io.methea.domain.configuration.user.entity;

import io.methea.domain.baseentity.BaseEntity;
import io.methea.domain.configuration.permission.TUserPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
@Entity
@Table(name = "tbl_core_user")
public class TUser extends BaseEntity implements UserDetails {

    @Id
    @Column(name = "user_id")
    private String id;
    private String groupId;
    private String username;
    private String password;
    @Transient
    private List<TUserPermission> URIs;

    public TUser(){}

    public TUser(String username, String password, List<TUserPermission> URIs){
        this.username = username;
        this.password = password;
        this.URIs = URIs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if (StringUtils.isEmpty(id)) {
            this.id = UUID.randomUUID().toString();
        }
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public List<TUserPermission> getURIs() {
        return URIs;
    }

    public void setURIs(List<TUserPermission> URIs) {
        this.URIs = URIs;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return URIs;
    }
}
