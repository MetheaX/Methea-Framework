package io.methea.service.eventlistener;

import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 31/05/2020
 */
@Service
public class RoleEventListener {

    //private final RoleURIRepository repository;
//    private final RoleURIService roleURIService;
//    private final InternalPermissionHelperService helperService;
    // final RMUserGrantedPermissionRepository viewPermissionRepository;
//    private final UserGrantedPermissionRepository userGrantedPermissionRepository;

//    public RoleEventListener(RoleURIRepository repository, RoleURIService roleURIService,
//                             InternalPermissionHelperService helperService, RMUserGrantedPermissionRepository viewPermissionRepository,
//                             UserGrantedPermissionRepository userGrantedPermissionRepository) {
//        this.repository = repository;
//        this.roleURIService = roleURIService;
//        this.helperService = helperService;
//        this.viewPermissionRepository = viewPermissionRepository;
//        this.userGrantedPermissionRepository = userGrantedPermissionRepository;
//    }

//    @Async
//    @Transactional
//    @EventListener(condition = "#entity.activate")
//    public void handleRoleActivatedEvent(TRole entity) {
//        List<TRoleURI> roleURIS = repository.findAllByRoleId(entity.getId());
//        if (!CollectionUtils.isEmpty(roleURIS)) {
//            for (TRoleURI roleURI : roleURIS) {
//                roleURIService.activateEntity(roleURI.getId());
//                List<TRMUserPermission> permissions = viewPermissionRepository.findAllByRoleIdAndStatus(roleURI.getRoleId(),
//                        MConstant.INACTIVE_STATUS);
//                if (!CollectionUtils.isEmpty(permissions)) {
//                    for (TRMUserPermission perm : permissions) {
//                        perm.setStatus(MConstant.ACTIVE_STATUS);
//                        viewPermissionRepository.save(perm);
//                        helperService.saveInternalPermissionRoleBase(perm);
//                    }
//                }
//            }
//        }
//    }

//    @Async
//    @Transactional
//    @EventListener(condition = "#entity.deactivate")
//    public void handleRoleDeactivatedEvent(TRole entity) {
//        List<TRoleURI> roleURIS = repository.findAllByRoleId(entity.getId());
//        if (!CollectionUtils.isEmpty(roleURIS)) {
//            for (TRoleURI roleURI : roleURIS) {
//                roleURIService.deactivateEntity(roleURI.getId());
//                List<TRMUserPermission> permissions = viewPermissionRepository.findAllByRoleIdAndStatus(roleURI.getRoleId(),
//                        MConstant.ACTIVE_STATUS);
//                if (!CollectionUtils.isEmpty(permissions)) {
//                    for (TRMUserPermission perm : permissions) {
//                        perm.setStatus(MConstant.INACTIVE_STATUS);
//                        viewPermissionRepository.save(perm);
//                        userGrantedPermissionRepository.deleteExistingPermByView(perm.getId());
//                    }
//                }
//            }
//        }
//    }
}
