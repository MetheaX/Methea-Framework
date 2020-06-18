package io.methea.service.eventlistener;

import io.methea.constant.MConstant;
import io.methea.domain.configuration.group.entity.TUserGroup;
import io.methea.domain.configuration.permission.entity.TRMUserPermission;
import io.methea.domain.configuration.permission.entity.TUserPermission;
import io.methea.domain.configuration.role.entity.TRole;
import io.methea.domain.configuration.uri.entity.TRoleURI;
import io.methea.domain.configuration.user.entity.TUser;
import io.methea.exception.GroupInactiveException;
import io.methea.exception.RoleInactiveException;
import io.methea.exception.UserInactiveException;
import io.methea.repository.configuration.group.UserGroupRepository;
import io.methea.repository.configuration.permission.RMUserGrantedPermissionRepository;
import io.methea.repository.configuration.permission.UserGrantedPermissionRepository;
import io.methea.repository.configuration.role.UserRoleRepository;
import io.methea.repository.configuration.uri.RoleURIRepository;
import io.methea.repository.configuration.user.UserRepository;
import io.methea.service.eventlistener.helper.InternalPermissionHelperService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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
    private final UserGroupRepository groupRepository;
    private final UserRepository userRepository;
    private final UserGrantedPermissionRepository userGrantedPermissionRepository;
    private final RMUserGrantedPermissionRepository viewPermissionRepository;
    private final InternalPermissionHelperService helperService;

    @Inject
    public ConfigurationEventListener(RoleURIRepository repository, UserRoleRepository roleRepository,
                                      UserGroupRepository groupRepository, UserRepository userRepository,
                                      UserGrantedPermissionRepository userGrantedPermissionRepository,
                                      RMUserGrantedPermissionRepository viewPermissionRepository,
                                      InternalPermissionHelperService helperService) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.userGrantedPermissionRepository = userGrantedPermissionRepository;
        this.viewPermissionRepository = viewPermissionRepository;
        this.helperService = helperService;
    }

    @Async
    @Transactional
    @EventListener(condition = "#entity.create")
    public void handleUserCreatedEvent(TUser entity) {
        TRole role = roleRepository.findTRoleByName(SYS_STARTER);
        List<TRoleURI> roleURIS = repository.findAllByRoleId(role.getId());

        TRMUserPermission viewPermission = new TRMUserPermission();
        viewPermission.setId(UUID.randomUUID().toString());
        viewPermission.setFirstName(entity.getFirstName());
        viewPermission.setLastName(entity.getLastName());
        viewPermission.setRoleId(role.getId());
        viewPermission.setRoleName(role.getName());
        viewPermission.setUserLoginId(entity.getUsername());
        viewPermission.setStatus(MConstant.ACTIVE_STATUS);

        viewPermissionRepository.save(viewPermission);

        for (TRoleURI o : roleURIS) {
            TUserPermission permission = new TUserPermission();
            permission.setId(UUID.randomUUID().toString());
            permission.setRoleUserId(o.getId());
            permission.setViewId(viewPermission.getId());
            permission.setUriName(o.getUriName());
            permission.setUserId(entity.getUsername());

            permission.setStatus(o.getStatus());
            userGrantedPermissionRepository.save(permission);
        }
    }

    @Async
    @Transactional
    @EventListener(condition = "#entity.activate")
    public void handleUserActivated(TUser entity) {
        Optional<TUserGroup> obj = groupRepository.findById(entity.getGroupId());
        if (obj.isPresent()) {
            TUserGroup group = obj.get();
            if (MConstant.INACTIVE_STATUS.equals(group.getStatus())) {
                throw new GroupInactiveException(String.format("User cannot active, group [%s] inactive!", group.getGroupName()));
            }
        }
        onUserActivatedOrDeactivated(entity);
        List<TRMUserPermission> viewPermissions = viewPermissionRepository.findAllByUserLoginId(entity.getUsername());
        for (TRMUserPermission perm : viewPermissions) {
            helperService.saveInternalPermissionRoleBase(perm);
        }
    }

    @Async
    @Transactional
    @EventListener(condition = "#entity.deactivate")
    public void handleUserDeactivated(TUser entity) {
        userGrantedPermissionRepository.deleteExistingPerm(entity.getUsername());
        onUserActivatedOrDeactivated(entity);
    }

    @Async
    @Transactional
    @EventListener(condition = "#entity.update")
    public void handlePermissionChangedEvent(TRMUserPermission entity) {
        userGrantedPermissionRepository.deleteExistingPerm(entity.getUserLoginId(), entity.getId());
        helperService.saveInternalPermissionRoleBase(entity);
    }

    @Async
    @Transactional
    @EventListener(condition = "#entity.create")
    public void handlePermissionAddedEvent(TRMUserPermission entity) {
        helperService.saveInternalPermissionRoleBase(entity);
    }

    @Async
    @Transactional
    @EventListener(condition = "#entity.activate")
    public void handlePermissionActivated(TRMUserPermission entity) {
        TUser user = userRepository.findByUsername(entity.getUserLoginId());
        if (!ObjectUtils.isEmpty(user) && MConstant.INACTIVE_STATUS.equals(user.getStatus())) {
            throw new UserInactiveException(String.format("Cannot activate permission, user [%s] inactive!", user.getUsername()));
        }
        Optional<TRole> obj = roleRepository.findById(entity.getRoleId());
        if (obj.isPresent()) {
            TRole role = obj.get();
            if (MConstant.INACTIVE_STATUS.equals(role.getStatus())) {
                throw new RoleInactiveException(String.format("Cannot activate permission, role [%s] inactive!", role.getName()));
            }
        }
    }

    private void onUserActivatedOrDeactivated(TUser entity) {
        List<TRMUserPermission> viewPermissions = viewPermissionRepository.findAllByUserLoginId(entity.getUsername());
        for (TRMUserPermission perm : viewPermissions) {
            if (!ObjectUtils.isEmpty(viewPermissions)) {
                perm.setStatus(entity.getStatus());
                viewPermissionRepository.save(perm);
            }
        }
    }
}
