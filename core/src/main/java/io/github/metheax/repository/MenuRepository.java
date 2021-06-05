package io.github.metheax.repository;

import io.github.metheax.domain.entity.TMenu;
import io.github.metheax.domain.view.MenuView;
import io.github.metheax.repository.hibernateextension.HibernateExtensionRepository;
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
