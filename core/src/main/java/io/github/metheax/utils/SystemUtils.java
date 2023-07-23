package io.github.metheax.utils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Author : Kuylim Tith
 * Date : 9/8/2019
 */
public class SystemUtils {
    private SystemUtils() {
    }

    public static String getBaseUrl(HttpServletRequest request) {
        return request.getRequestURL().substring(0, request.getRequestURL().length() - request.getRequestURI().length()) + request.getContextPath();
    }
}
