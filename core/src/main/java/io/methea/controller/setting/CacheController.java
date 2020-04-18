package io.methea.controller.setting;

import io.methea.cache.MCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author : DKSilverX
 * Date : 18/04/2020
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping(value = {CacheController.ROOT_URL})
public class CacheController {
    private static Logger log = LoggerFactory.getLogger(CacheController.class);

    static final String ROOT_URL = "/app/clear-caches";
    private static final String REDIRECT_URL = "redirect:/app";

    @RequestMapping
    public ModelAndView clearSystemCache(){
        MCache.cacheMetaData.clear();
        log.info(">>>>> Internal system cache clear!");
        return new ModelAndView(REDIRECT_URL);
    }
}
