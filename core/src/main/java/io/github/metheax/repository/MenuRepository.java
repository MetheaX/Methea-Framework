package io.github.metheax.repository;

import io.github.metheax.domain.entity.TMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author : Kuylim Tith
 * Date : 04/07/2020
 */
public interface MenuRepository extends JpaRepository<TMenu, String> {
    List<TMenu> findAllByGroupIdAndStatus(String groupId, String status);
    List<TMenu> findAllByStatusOrderByIndexAsc(String status);
}
