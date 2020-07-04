package io.methea.repository.configuration.menu;

import io.methea.domain.configuration.menu.entity.TMenu;
import io.methea.domain.configuration.menu.view.MenuView;
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
