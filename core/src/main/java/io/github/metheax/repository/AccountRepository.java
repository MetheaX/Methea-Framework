package io.github.metheax.repository;

import io.github.metheax.domain.entity.TAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : Kuylim Tith
 * Date : 21/08/2019
 */
public interface AccountRepository extends JpaRepository<TAccount, String> {
}
