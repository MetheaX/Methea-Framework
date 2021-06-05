package io.github.metheax.web.controller;

import io.github.metheax.cache.MetheaCache;
import io.github.metheax.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

/**
 * Author : DKSilverX
 * Date : 18/04/2020
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping(value = {CacheController.ROOT_URL})
public class CacheController {

    static final String ROOT_URL = "/app/clear-caches";
    private static final String REDIRECT_URL = "redirect:/app";

    private final MenuService menuService;

    @Inject
    public CacheController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(value = StringUtils.EMPTY)
    public ModelAndView clearSystemCache() {
        MetheaCache.CACHE_MENU.clear();
        MetheaCache.CACHE_MENU.putAll(menuService.getAllMenuByGroup());
        return new ModelAndView(REDIRECT_URL);
    }
}
