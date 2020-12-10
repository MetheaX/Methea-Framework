package io.methea.utils;

import io.methea.constant.MConstant;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * Author : DKSilverX
 * Date : 26/04/2020
 */
public class MBeanUtils {

    private MBeanUtils(){}

    public static String[] getNullProperties(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] properties = src.getPropertyDescriptors();

        Set<String> nullProperties = new HashSet<>();
        for (java.beans.PropertyDescriptor property : properties) {
            Object srcValue = src.getPropertyValue(property.getName());
            if (null == srcValue || MConstant.ID.equals(property.getName())) nullProperties.add(property.getName());
        }

        String[] result = new String[nullProperties.size()];
        return nullProperties.toArray(result);
    }
}
