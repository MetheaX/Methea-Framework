package io.methea.service.eventlistener.helper;

//import io.methea.domain.configuration.permission.entity.TRMUserPermission;
//import io.methea.domain.configuration.resource.entity.TRoleURI;
//import io.methea.repository.configuration.uri.RoleURIRepository;
import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 31/05/2020
 */
@Service
public class InternalPermissionHelperService {

//    private final RoleURIRepository repository;
//    private final UserGrantedPermissionRepository userGrantedPermissionRepository;
//    private final APIBaseRepository apiBaseRepository;
//
//    @Inject
//    public InternalPermissionHelperService(RoleURIRepository repository,
//                                           UserGrantedPermissionRepository userGrantedPermissionRepository,
//                                           APIBaseRepository apiBaseRepository) {
//        this.repository = repository;
//        this.userGrantedPermissionRepository = userGrantedPermissionRepository;
//        this.apiBaseRepository = apiBaseRepository;
//    }
//
//    public void saveInternalPermissionRoleBase(TRMUserPermission entity) {
//        List<TRoleURI> roleURIS = repository.findAllByRoleId(entity.getRoleId());
//        for (TRoleURI o : roleURIS) {
//            TPermission permission = new TPermission();
//            permission.setId(UUID.randomUUID().toString());
//            permission.setRoleUserId(o.getId());
//            permission.setUriName(o.getUriName());
//            permission.setUserId(entity.getUserLoginId());
//            permission.setViewId(entity.getId());
//
//            permission.setStatus(o.getStatus());
//            userGrantedPermissionRepository.save(permission);
//        }
//    }
//
//    public void saveInternalPermissionURIBase(TRoleURI roleURI) {
//        userGrantedPermissionRepository.updateExistingPermURIBase(roleURI.getStatus(), roleURI.getId());
//    }
//
//    public void saveClientPermission(String clientId, List<String> apiBases) {
//        List<APIBase> apiBaseList = apiBaseRepository.findAllByIdIn(apiBases);
//        if (CollectionUtils.isEmpty(apiBaseList)) {
//           throw new APIBaseNotFoundException("=========> Provided invalid base api.");
//        }
//        for (APIBase apiBase : apiBaseList) {
//            TPermission permission = new TPermission();
//            permission.setId(UUID.randomUUID().toString());
//            permission.setRoleUserId(StringUtils.EMPTY);
//            permission.setUriName(apiBase.getApiUrl());
//            permission.setUserId(clientId);
//
//            permission.setStatus(MConstant.ACTIVE_STATUS);
//            userGrantedPermissionRepository.save(permission);
//        }
//    }
//
//    public void revokePermissionBaseOnClientID(String clientID) {
//        userGrantedPermissionRepository.deleteExistingPerm(clientID);
//    }
}
