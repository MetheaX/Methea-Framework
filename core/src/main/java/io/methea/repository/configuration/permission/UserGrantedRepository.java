package io.methea.repository.configuration.permission;

import io.methea.domain.configuration.permission.TUserPermission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author : DKSilverX
 * Date : 15/09/2019
 */
public interface UserGrantedRepository extends CrudRepository<TUserPermission, String> {
    List<TUserPermission> findAllByUserId(String userId);
}
