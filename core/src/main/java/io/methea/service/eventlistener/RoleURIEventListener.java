package io.methea.service.eventlistener;

import io.methea.constant.MConstant;
import io.methea.domain.configuration.role.entity.TRole;
import io.methea.domain.configuration.resource.entity.TResource;
import io.methea.exception.RoleInactiveException;
import io.methea.exception.URIInactiveException;
import io.methea.repository.configuration.role.UserRoleRepository;
import io.methea.repository.configuration.uri.URIRepository;
import io.methea.service.eventlistener.helper.InternalPermissionHelperService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Author : DKSilverX
 * Date : 31/05/2020
 */
@Service
public class RoleURIEventListener {

    private final InternalPermissionHelperService helperService;
    private final UserRoleRepository roleRepository;
    private final URIRepository uriRepository;

    public RoleURIEventListener(InternalPermissionHelperService helperService, UserRoleRepository roleRepository,
                                URIRepository uriRepository) {
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
