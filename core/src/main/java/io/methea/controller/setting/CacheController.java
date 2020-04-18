package io.methea.controller.setting;

import io.methea.cache.MCache;
import io.methea.controller.abs.MBaseController;
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
public class CacheController extends MBaseController {
    private static Logger log = LoggerFactory.getLogger(CacheController.class);

    private static final String CLEAR_CACHE_URL = "/clear-caches";
    private static final String REDIRECT_URL = "redirect:/app";

    @RequestMapping(value = {CLEAR_CACHE_URL})
    public ModelAndView clearSystemCache(){
        MCache.cacheMetaData.clear();
        log.info(">>>>> Internal system cache clear!");
        return new ModelAndView(REDIRECT_URL);
    }
}
