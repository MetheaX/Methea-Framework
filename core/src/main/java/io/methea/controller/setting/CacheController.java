package io.methea.controller.setting;

import io.methea.cache.MCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    static final String ROOT_URL = "/app/clear-caches";
    private static final String REDIRECT_URL = "redirect:/app";

    @RequestMapping(value = StringUtils.EMPTY, method = RequestMethod.GET)
    public ModelAndView clearSystemCache(){
        MCache.cacheMetaData.clear();
        return new ModelAndView(REDIRECT_URL);
    }
}
