package io.methea.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author : DKSilverX
 * Date : 18/01/2020
 */
public class MDateUtil {

    private static Logger log = LoggerFactory.getLogger(MDateUtil.class);

    private MDateUtil(){}

    private static final String pattern = "DD/MM/YYYY HH:mm:ss";
    public static String convertDateToString(LocalDateTime date){
        String dateAsString = StringUtils.EMPTY;
        try{
            DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
            dateAsString = date.format(df);
        }catch (Exception ex){
            log.error(">>>>> Convert datetime to string error: ", ex);
        }
        return dateAsString;
    }
}
