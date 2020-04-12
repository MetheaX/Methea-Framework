package io.methea.repository.customnative;

import java.util.List;

/**
 * Author : DKSilverX
 * Date : 03/03/2020
 */
public interface CustomNativeRepository {
    List<Object[]> getByNativeQuery(String nativeSQL);
}
