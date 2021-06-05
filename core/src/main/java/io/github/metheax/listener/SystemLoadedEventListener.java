package io.github.metheax.listener;

import io.github.metheax.cache.MetheaCache;
import io.github.metheax.service.MenuService;
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

    private final MenuService service;

    @Inject
    public SystemLoadedEventListener(MenuService service) {
        this.service = service;
    }

    @Lazy
    @EventListener({ContextRefreshedEvent.class})
    public void onContextRefreshEvent() {
        MetheaCache.CACHE_MENU.putAll(service.getAllMenuByGroup());
    }
}
