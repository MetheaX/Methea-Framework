package io.methea.service.eventlistener;

import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 31/05/2020
 */
@Service
public class URIEventListener {

//    private final RoleURIRepository repository;
//    private final InternalPermissionHelperService helperService;
//
//    public URIEventListener(RoleURIRepository repository, InternalPermissionHelperService helperService) {
//        this.repository = repository;
//        this.helperService = helperService;
//    }
//
//    @Async
//    @Transactional
//    @EventListener(condition = "#entity.activate")
//    public void handleRoleActivatedEvent(TResource entity) {
//        repository.updateExistingRoleURIBase(entity.getStatus(), entity.getId());
//        List<TRoleURI> roleURIS = repository.findAllByUriId(entity.getId());
//        for (TRoleURI roleURI : roleURIS) {
//            helperService.saveInternalPermissionURIBase(roleURI);
//        }
//    }
//
//    @Async
//    @Transactional
//    @EventListener(condition = "#entity.deactivate")
//    public void handleRoleDeactivatedEvent(TResource entity) {
//        repository.updateExistingRoleURIBase(entity.getStatus(), entity.getId());
//        List<TRoleURI> roleURIS = repository.findAllByUriId(entity.getId());
//        for (TRoleURI roleURI : roleURIS) {
//            helperService.saveInternalPermissionURIBase(roleURI);
//        }
//    }
}
