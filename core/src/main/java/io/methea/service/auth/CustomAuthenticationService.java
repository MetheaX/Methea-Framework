package io.methea.service.auth;

import io.methea.config.security.domain.PrincipalAuthentication;
import io.methea.domain.configuration.group.projection.GroupAuthorityProjection;
import io.methea.domain.configuration.permission.projection.PermissionProjection;
import io.methea.domain.configuration.user.entity.TUser;
import io.methea.repository.configuration.group.UserGroupRepository;
import io.methea.repository.configuration.permission.UserGrantedPermissionRepository;
import io.methea.repository.configuration.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
public class CustomAuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserGrantedPermissionRepository userGrantedPermissionRepository;
    private final UserGroupRepository userGroupRepository;

    @Inject
    public CustomAuthenticationService(UserRepository userRepository, UserGrantedPermissionRepository userGrantedPermissionRepository,
                                       UserGroupRepository userGroupRepository) {
        this.userRepository = userRepository;
        this.userGrantedPermissionRepository = userGrantedPermissionRepository;
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    public PrincipalAuthentication loadUserByUsername(String s) throws UsernameNotFoundException {
        TUser user = userRepository.findByUsername(s);

        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        Map<String, Object> param = new HashMap<>();
        param.put("username", s);
        return new PrincipalAuthentication(user.getUsername(), user.getPassword(),
                userGroupRepository.getByQuery(param, GroupAuthorityProjection.class),
                userGrantedPermissionRepository.getByQuery(param, PermissionProjection.class));
    }
}
