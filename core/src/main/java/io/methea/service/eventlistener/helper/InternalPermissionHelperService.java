package io.methea.service.eventlistener.helper;

import io.methea.constant.MConstant;
import io.methea.domain.configuration.permission.entity.TRMUserPermission;
import io.methea.domain.configuration.permission.entity.TUserPermission;
import io.methea.domain.configuration.uri.entity.TRoleURI;
import io.methea.domain.webservice.baseapi.entity.APIBase;
import io.methea.exception.APIBaseNotFoundException;
import io.methea.repository.configuration.permission.UserGrantedPermissionRepository;
import io.methea.repository.configuration.uri.RoleURIRepository;
import io.methea.repository.webservice.baseapi.APIBaseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 31/05/2020
 */
@Service
public class InternalPermissionHelperService {

    private final RoleURIRepository repository;
    private final UserGrantedPermissionRepository userGrantedPermissionRepository;
    private final APIBaseRepository apiBaseRepository;

    @Inject
    public InternalPermissionHelperService(RoleURIRepository repository,
                                           UserGrantedPermissionRepository userGrantedPermissionRepository,
                                           APIBaseRepository apiBaseRepository) {
        this.repository = repository;
        this.userGrantedPermissionRepository = userGrantedPermissionRepository;
        this.apiBaseRepository = apiBaseRepository;
    }

    public void saveInternalPermissionRoleBase(TRMUserPermission entity) {
        List<TRoleURI> roleURIS = repository.findAllByRoleId(entity.getRoleId());
        for (TRoleURI o : roleURIS) {
            TUserPermission permission = new TUserPermission();
            permission.setId(UUID.randomUUID().toString());
            permission.setRoleUserId(o.getId());
            permission.setUriName(o.getUriName());
            permission.setUserId(entity.getUserLoginId());
            permission.setViewId(entity.getId());

            permission.setStatus(o.getStatus());
            userGrantedPermissionRepository.save(permission);
        }
    }

    public void saveInternalPermissionURIBase(TRoleURI roleURI) {
        userGrantedPermissionRepository.updateExistingPermURIBase(roleURI.getStatus(), roleURI.getId());
    }

    public void saveClientPermission(String clientId, List<String> apiBases) {
        List<APIBase> apiBaseList = apiBaseRepository.findAllByIdIn(apiBases);
        if (CollectionUtils.isEmpty(apiBaseList)) {
           throw new APIBaseNotFoundException("=========> Provided invalid base api.");
        }
        for (APIBase apiBase : apiBaseList) {
            TUserPermission permission = new TUserPermission();
            permission.setId(UUID.randomUUID().toString());
            permission.setRoleUserId(StringUtils.EMPTY);
            permission.setUriName(apiBase.getApiUrl());
            permission.setUserId(clientId);

            permission.setStatus(MConstant.ACTIVE_STATUS);
            userGrantedPermissionRepository.save(permission);
        }
    }

    public void revokePermissionBaseOnClientID(String clientID) {
        userGrantedPermissionRepository.deleteExistingPerm(clientID);
    }
}
