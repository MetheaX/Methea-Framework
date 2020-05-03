package io.methea.utils;

/**
 * Author : DKSilverX
 * Date : 14/04/2020
 */
import org.apache.commons.lang3.StringUtils;

public class MStringUtils {
    private MStringUtils(){}

    public static String nullToEmptyOrValue(String text){
        return StringUtils.isEmpty(text) ? StringUtils.EMPTY : text.trim();
    }
}
