package io.methea.service.abs;

import io.methea.utils.Pagination;

import java.util.List;
import java.util.Map;

public interface SimpleMetheaService<E, B, ID, V> {

    E saveEntity(E entity, B binder);

    E modifyEntity(ID id, B binder);

    boolean activateEntity(ID id);

    boolean deactivateEntity(ID id);

    List<V> getAllEntityViewByFilter(Map<String, Object> param, Pagination pagination);

    V getEntityViewById(ID id);
}
