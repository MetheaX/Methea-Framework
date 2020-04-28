package io.methea.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Author : DKSilverX
 * Date : 26/04/2020
 */
public class MBeanUtils {
    public static String[] getNullProperties(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] properties = src.getPropertyDescriptors();

        Set<String> nullProperties = new HashSet<>();
        for (java.beans.PropertyDescriptor property : properties) {
            Object srcValue = src.getPropertyValue(property.getName());
            if (ObjectUtils.isEmpty(srcValue)) nullProperties.add(property.getName());
        }

        String[] result = new String[nullProperties.size()];
        return nullProperties.toArray(result);
    }
}
