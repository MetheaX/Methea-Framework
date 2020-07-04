package io.methea.listener;

import io.methea.cache.MCache;
import io.methea.service.configuration.menu.MMenuService;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Author : DKSilverX
 * Date : 04/07/2020
 */
@Component
public class SystemLoadedEventListener {

    private final MMenuService service;

    @Inject
    public SystemLoadedEventListener(MMenuService service) {
        this.service = service;
    }

    @Lazy
    @EventListener({ContextRefreshedEvent.class})
    public void onContextRefreshEvent() {
        MCache.CACHE_MENU.putAll(service.getAllMenuByGroup());
    }
}
