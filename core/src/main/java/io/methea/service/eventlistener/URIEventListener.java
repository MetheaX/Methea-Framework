package io.methea.service.eventlistener;

import io.methea.domain.configuration.uri.entity.TMstURI;
import io.methea.domain.configuration.uri.entity.TRoleURI;
import io.methea.repository.configuration.uri.RoleURIRepository;
import io.methea.service.eventlistener.helper.InternalPermissionHelperService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Author : DKSilverX
 * Date : 31/05/2020
 */
@Service
public class URIEventListener {

    private final RoleURIRepository repository;
    private final InternalPermissionHelperService helperService;

    public URIEventListener(RoleURIRepository repository, InternalPermissionHelperService helperService) {
        this.repository = repository;
        this.helperService = helperService;
    }

    @Async
    @Transactional
    @EventListener(condition = "#entity.activate")
    public void handleRoleActivatedEvent(TMstURI entity) {
        repository.updateExistingRoleURIBase(entity.getStatus(), entity.getId());
        List<TRoleURI> roleURIS = repository.findAllByUriId(entity.getId());
        for (TRoleURI roleURI : roleURIS) {
            helperService.saveInternalPermissionURIBase(roleURI);
        }
    }

    @Async
    @Transactional
    @EventListener(condition = "#entity.deactivate")
    public void handleRoleDeactivatedEvent(TMstURI entity) {
        repository.updateExistingRoleURIBase(entity.getStatus(), entity.getId());
        List<TRoleURI> roleURIS = repository.findAllByUriId(entity.getId());
        for (TRoleURI roleURI : roleURIS) {
            helperService.saveInternalPermissionURIBase(roleURI);
        }
    }
}
