package io.methea.cache;

import io.methea.domain.configuration.menu.view.MenuRender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 19/01/2020
 */
public class MCache {
    private MCache(){}

    public static final Map<String, Object> CACHE_META_DATA = new HashMap<>();

    public static final Map<String, List<MenuRender>> CACHE_MENU = new HashMap<>();
}
