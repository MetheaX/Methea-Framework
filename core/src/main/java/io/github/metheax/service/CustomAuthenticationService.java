package io.github.metheax.service;

import io.github.metheax.config.security.MetheaPrincipal;
import io.github.metheax.config.security.PrincipalAuthentication;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.repository.UserGrantedPermissionRepository;
import io.github.metheax.domain.view.GroupAuthorityView;
import io.github.metheax.domain.view.PermissionView;
import io.github.metheax.domain.entity.TUser;
import io.github.metheax.repository.GroupRepository;
import io.github.metheax.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
@Service
@Transactional
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CustomAuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserGrantedPermissionRepository userGrantedPermissionRepository;
    private final GroupRepository userGroupRepository;

    @Inject
    public CustomAuthenticationService(UserRepository userRepository, UserGrantedPermissionRepository userGrantedPermissionRepository,
                                       GroupRepository userGroupRepository) {
        this.userRepository = userRepository;
        this.userGrantedPermissionRepository = userGrantedPermissionRepository;
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    public PrincipalAuthentication loadUserByUsername(String s) throws UsernameNotFoundException {
        TUser user = userRepository.findByUsernameAndStatus(s, MetheaConstant.ACTIVE_STATUS);

        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        MetheaPrincipal principal = new MetheaPrincipal();
        Map<String, Object> param = new HashMap<>();
        param.put("username", s);
        BeanUtils.copyProperties(user, principal);
        return new PrincipalAuthentication(user.getUsername(), user.getPassword(),
                userGroupRepository.getByQuery(param, GroupAuthorityView.class),
                userGrantedPermissionRepository.getByQuery(param, PermissionView.class), principal);
    }
}
