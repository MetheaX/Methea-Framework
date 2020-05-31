package io.methea.service.eventlistener;

import io.methea.domain.configuration.uri.entity.TRoleURI;
import io.methea.service.eventlistener.helper.InternalPermissionHelperService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Author : DKSilverX
 * Date : 31/05/2020
 */
@Service
public class RoleURIEventListener {

    private final InternalPermissionHelperService helperService;

    public RoleURIEventListener(InternalPermissionHelperService helperService) {
        this.helperService = helperService;
    }

    @Async
    @Transactional
    @EventListener(condition = "#entity.activate")
    public void handleRoleURIActivatedEvent(TRoleURI entity) {
        helperService.saveInternalPermissionURIBase(entity);
    }

    @Async
    @Transactional
    @EventListener(condition = "#entity.deactivate")
    public void handleRoleURIDeactivateEvent(TRoleURI entity) {
        helperService.saveInternalPermissionURIBase(entity);
    }
}
