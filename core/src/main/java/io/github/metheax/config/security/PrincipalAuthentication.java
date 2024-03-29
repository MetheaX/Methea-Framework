package io.github.metheax.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.CollectionUtils;

import java.io.Serial;
import java.util.*;

/**
 * Author : Kuylim Tith
 * Date : 16/04/2020
 */
public class PrincipalAuthentication extends User {

    @Serial
    private static final long serialVersionUID = 1710102819742330038L;
    private final List<GrantedPermission> grantedPermissions;
    private final MetheaPrincipal metheaPrincipal;

    public PrincipalAuthentication(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.grantedPermissions = new ArrayList<>();
        this.metheaPrincipal = null;
    }

    public PrincipalAuthentication(String username, String password, Collection<? extends GrantedAuthority> authorities,
                                   Collection<? extends GrantedPermission> grantedPermissions) {
        super(username, password, authorities);
        this.grantedPermissions = validatedGrantedPermission(new ArrayList<>(grantedPermissions));
        this.metheaPrincipal = null;
    }

    public PrincipalAuthentication(String username, String password, Collection<? extends GrantedAuthority> authorities,
                                   Collection<? extends GrantedPermission> grantedPermissions, MetheaPrincipal principal) {
        super(username, password, authorities);
        this.grantedPermissions = validatedGrantedPermission(new ArrayList<>(grantedPermissions));
        this.metheaPrincipal = principal;
    }

    public PrincipalAuthentication(String username, String password, boolean enabled, boolean accountNonExpired,
                                   boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.grantedPermissions = new ArrayList<>();
        this.metheaPrincipal = null;
    }

    private List<GrantedPermission> validatedGrantedPermission(List<GrantedPermission> grantedPermissions) {
        if (CollectionUtils.isEmpty(grantedPermissions)) {
            return new ArrayList<>();
        }
        return grantedPermissions;
    }

    public List<GrantedPermission> getGrantedPermissions() {
        return grantedPermissions;
    }

    public MetheaPrincipal getMetheaPrincipal() {
        return metheaPrincipal;
    }
}
