package io.methea.repository.configuration.account;

import io.methea.domain.configuration.account.entity.TAccount;
import io.methea.domain.configuration.account.view.AccountView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
public interface AccountRepository extends CrudRepository<TAccount, String>, HibernateExtensionRepository<AccountView, String> {
}
