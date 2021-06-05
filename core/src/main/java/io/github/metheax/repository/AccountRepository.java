package io.github.metheax.repository;

import io.github.metheax.domain.view.AccountView;
import io.github.metheax.domain.entity.TAccount;
import io.github.metheax.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
public interface AccountRepository extends CrudRepository<TAccount, String>, HibernateExtensionRepository<AccountView, String> {
}
