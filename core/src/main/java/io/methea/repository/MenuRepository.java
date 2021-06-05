package io.methea.repository;

import io.methea.domain.entity.TMenu;
import io.methea.domain.view.MenuView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author : DKSilverX
 * Date : 04/07/2020
 */
public interface MenuRepository extends CrudRepository<TMenu, String>,
        HibernateExtensionRepository<MenuView, String> {
    List<TMenu> findAllByGroupIdAndStatus(String groupId, String status);
    List<TMenu> findAllByStatusOrderByIndexAsc(String status);
}
