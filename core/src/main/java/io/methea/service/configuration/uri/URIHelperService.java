package io.methea.service.configuration.uri;

import io.methea.domain.configuration.permission.entity.TUserPermission;
import io.methea.domain.configuration.role.entity.TRole;
import io.methea.domain.configuration.uri.TRoleURI;
import io.methea.domain.configuration.user.entity.TUser;
import io.methea.repository.configuration.permission.UserGrantedPermissionRepository;
import io.methea.repository.configuration.role.UserRoleRepository;
import io.methea.repository.configuration.uri.RoleUIRRepository;
import io.methea.util.PrincipalUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 02/05/2020
 */
@Service
public class URIHelperService {

    private static final String SYS_STARTER = "ROLE_SYS_STARTER";
    private final RoleUIRRepository repository;
    private final UserRoleRepository roleRepository;
    private final UserGrantedPermissionRepository userGrantedPermissionRepository;
    private final HttpServletRequest request;

    @Inject
    public URIHelperService(RoleUIRRepository repository, UserRoleRepository roleRepository,
                            UserGrantedPermissionRepository userGrantedPermissionRepository, HttpServletRequest request) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.userGrantedPermissionRepository = userGrantedPermissionRepository;
        this.request = request;
    }

    @Async
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

            permission.setCreatedUser(PrincipalUtils.getUserLoginId(request));
            permission.setUpdatedUser(PrincipalUtils.getUserLoginId(request));
            permission.setCreatedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),
                    ZoneId.systemDefault()));
            permission.setUpdatedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),
                    ZoneId.systemDefault()));

            userGrantedPermissionRepository.save(permission);
        }
    }
}
