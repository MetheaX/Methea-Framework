package io.github.metheax.service;

import io.github.metheax.config.security.MetheaPrincipal;
import io.github.metheax.config.security.PrincipalAuthentication;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.repository.PermissionRepository;
import io.github.metheax.domain.view.GroupAuthorityView;
import io.github.metheax.domain.view.PermissionView;
import io.github.metheax.domain.entity.TUser;
import io.github.metheax.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : Kuylim Tith
 * Date : 21/08/2019
 */
@Service
@Transactional
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CustomAuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    @Inject
    public CustomAuthenticationService(UserRepository userRepository, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public PrincipalAuthentication loadUserByUsername(String s) throws UsernameNotFoundException {
        TUser user = userRepository.findByUsernameAndStatus(s, MetheaConstant.ACTIVE_STATUS);

        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        MetheaPrincipal principal = new MetheaPrincipal();
        Map<String, Object> param = new HashMap<>();
        param.put("roles", user.getRoles());
        BeanUtils.copyProperties(user, principal);
        principal.setGroupCode(user.getGroup().getGroupCode());
        GroupAuthorityView groupAuthorityView = new GroupAuthorityView(user.getGroup().getGroupCode());
        List<GroupAuthorityView> grantedAuthority = new ArrayList<>();
        grantedAuthority.add(groupAuthorityView);
        return new PrincipalAuthentication(user.getUsername(), user.getPassword(), grantedAuthority,
                permissionRepository.getByQuery(param, PermissionView.class), principal);
    }
}
