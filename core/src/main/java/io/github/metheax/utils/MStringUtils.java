package io.github.metheax.utils;

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

    public static String validateAndTrim(String text) {
        if (StringUtils.isEmpty(text)) {
            throw new RuntimeException("=========> Provided string value is empty");
        }
        text = text.trim();
        text = text.replaceAll("\\s+", " ");
        text = text.replaceAll("[\\p{Cf}]", StringUtils.EMPTY);
        return text;
    }

    public static String validateAndTrimOrEmpty(String text) {
        if (StringUtils.isEmpty(text)) {
            return StringUtils.EMPTY;
        }
        text = text.trim();
        text = text.replaceAll("\\s+", " ");
        text = text.replaceAll("[\\p{Cf}]", StringUtils.EMPTY);
        return text;
    }
}
