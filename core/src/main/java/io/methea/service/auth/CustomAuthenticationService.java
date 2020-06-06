package io.methea.service.auth;

import io.methea.config.security.domain.MetheaPrincipal;
import io.methea.config.security.domain.PrincipalAuthentication;
import io.methea.constant.MConstant;
import io.methea.domain.configuration.group.view.GroupAuthorityView;
import io.methea.domain.configuration.permission.view.PermissionView;
import io.methea.domain.configuration.user.entity.TUser;
import io.methea.domain.webservice.client.entity.Client;
import io.methea.domain.webservice.client.dto.ClientAuthentication;
import io.methea.repository.configuration.group.UserGroupRepository;
import io.methea.repository.configuration.permission.UserGrantedPermissionRepository;
import io.methea.repository.configuration.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
    private final ClientService clientService;

    @Inject
    public CustomAuthenticationService(UserRepository userRepository, UserGrantedPermissionRepository userGrantedPermissionRepository,
                                       UserGroupRepository userGroupRepository, ClientService clientService) {
        this.userRepository = userRepository;
        this.userGrantedPermissionRepository = userGrantedPermissionRepository;
        this.userGroupRepository = userGroupRepository;
        this.clientService = clientService;
    }

    @Override
    public PrincipalAuthentication loadUserByUsername(String s) throws UsernameNotFoundException {
        TUser user = userRepository.findByUsernameAndStatus(s, MConstant.ACTIVE_STATUS);

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

    public PrincipalAuthentication loadClientByClientId(ClientAuthentication authentication) throws UsernameNotFoundException {
        Client client = clientService.verifyClient(authentication);
        if (ObjectUtils.isEmpty(client)) {
            return null;
        }
        return initClientPrincipal(client);
    }

    public PrincipalAuthentication loadClientByClientId(String s) throws UsernameNotFoundException {
        Client client = clientService.getClientByClientId(s);
        if (ObjectUtils.isEmpty(client)) {
            return null;
        }
        return initClientPrincipal(client);
    }

    private PrincipalAuthentication initClientPrincipal(Client client) {
        MetheaPrincipal principal = new MetheaPrincipal();
        principal.setForceUserResetPassword("N");
        principal.setUsername(client.getClientId());
        Map<String, Object> param = new HashMap<>();
        param.put("username", principal.getUsername());
        return new PrincipalAuthentication(client.getClientId(), client.getClientSecret(), new ArrayList<>(),
                userGrantedPermissionRepository.getByQuery(param, PermissionView.class), principal);
    }
}
