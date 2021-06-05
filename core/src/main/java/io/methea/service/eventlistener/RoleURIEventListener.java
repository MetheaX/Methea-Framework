package io.methea.service.eventlistener;

import io.methea.repository.RoleRepository;
import io.methea.repository.ResourceRepository;
import io.methea.service.eventlistener.helper.InternalPermissionHelperService;
import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 31/05/2020
 */
@Service
public class RoleURIEventListener {

    private final InternalPermissionHelperService helperService;
    private final RoleRepository roleRepository;
    private final ResourceRepository uriRepository;

    public RoleURIEventListener(InternalPermissionHelperService helperService, RoleRepository roleRepository,
                                ResourceRepository uriRepository) {
        this.helperService = helperService;
        this.roleRepository = roleRepository;
        this.uriRepository = uriRepository;
    }
//
//    @Async
//    @Transactional
//    @EventListener(condition = "#entity.activate")
//    public void handleRoleURIActivatedEvent(TRoleURI entity) {
//        Optional<TRole> obj = roleRepository.findById(entity.getRoleId());
//        if (obj.isPresent()) {
//            TRole role = obj.get();
//            if (MConstant.INACTIVE_STATUS.equals(role.getStatus())) {
//                throw new RoleInactiveException(String.format("Cannot activate URI mapping, role [%s] inactive!", role.getName()));
//            }
//        }
//        Optional<TResource> o = uriRepository.findById(entity.getUriId());
//        if (o.isPresent()) {
//            TResource uri = o.get();
//            if (MConstant.INACTIVE_STATUS.equals(uri.getStatus())) {
//                throw new URIInactiveException(String.format("Cannot activate URI mapping, URI [%s] inactive!", uri.getUriName()));
//            }
//        }
//        helperService.saveInternalPermissionURIBase(entity);
//    }
//
//    @Async
//    @Transactional
//    @EventListener(condition = "#entity.deactivate")
//    public void handleRoleURIDeactivateEvent(TRoleURI entity) {
//        helperService.saveInternalPermissionURIBase(entity);
//    }
}
