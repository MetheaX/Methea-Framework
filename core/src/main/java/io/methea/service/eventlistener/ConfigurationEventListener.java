package io.methea.service.eventlistener;

import io.methea.constant.MConstant;
import io.methea.domain.configuration.permission.entity.TRMUserPermission;
import io.methea.domain.configuration.permission.entity.TUserPermission;
import io.methea.domain.configuration.role.entity.TRole;
import io.methea.domain.configuration.uri.entity.TRoleURI;
import io.methea.domain.configuration.user.entity.TUser;
import io.methea.repository.configuration.permission.UserGrantedPermissionRepository;
import io.methea.repository.configuration.role.UserRoleRepository;
import io.methea.repository.configuration.uri.RoleURIRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 02/05/2020
 */
@Service
public class ConfigurationEventListener {

    private static final String SYS_STARTER = "ROLE_SYS_STARTER";
    private final RoleURIRepository repository;
    private final UserRoleRepository roleRepository;
    private final UserGrantedPermissionRepository userGrantedPermissionRepository;

    @Inject
    public ConfigurationEventListener(RoleURIRepository repository, UserRoleRepository roleRepository,
                                      UserGrantedPermissionRepository userGrantedPermissionRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.userGrantedPermissionRepository = userGrantedPermissionRepository;
    }

    @Async
    @Transactional
    @EventListener
    public void handleUserCreatedEvent(TUser entity) {
        TRole role = roleRepository.findTRoleByName(SYS_STARTER);
        List<TRoleURI> roleURIS = repository.findAllByRoleId(role.getId());

        for (TRoleURI o : roleURIS) {
            TUserPermission permission = new TUserPermission();
            permission.setId(UUID.randomUUID().toString());
            permission.setRoleUserId(o.getId());
            permission.setUriName(o.getUriName());
            permission.setUserId(entity.getUsername());

            permission.setStatus(MConstant.ACTIVE_STATUS);
            userGrantedPermissionRepository.save(permission);
        }
    }

    @Async
    @Transactional
    @EventListener
    public void handlePermissionChangedEvent(TRMUserPermission entity) {
        userGrantedPermissionRepository.deleteExistingPerm(entity.getUserLoginId());

        List<TRoleURI> roleURIS = repository.findAllByRoleId(entity.getRoleId());
        for (TRoleURI o : roleURIS) {
            TUserPermission permission = new TUserPermission();
            permission.setId(UUID.randomUUID().toString());
            permission.setRoleUserId(o.getId());
            permission.setUriName(o.getUriName());
            permission.setUserId(entity.getUserLoginId());

            permission.setStatus(entity.getStatus());
            userGrantedPermissionRepository.save(permission);
        }
    }
}
