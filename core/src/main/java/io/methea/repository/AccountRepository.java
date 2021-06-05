package io.methea.repository;

import io.methea.domain.entity.TAccount;
import io.methea.domain.view.AccountView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
public interface AccountRepository extends CrudRepository<TAccount, String>, HibernateExtensionRepository<AccountView, String> {
}
