package io.github.metheax.cache;

import io.github.metheax.domain.entity.TGroup;
import io.github.metheax.domain.view.MenuRender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 19/01/2020
 */
public class MetheaCache {
    private MetheaCache(){}

    public static final Map<String, Object> CACHE_META_DATA = new HashMap<>();

    public static final Map<TGroup, List<MenuRender>> CACHE_MENU = new HashMap<>();
}
