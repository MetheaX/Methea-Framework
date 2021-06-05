package io.methea.service;

import io.methea.domain.binder.AccountBinder;
import io.methea.domain.entity.TAccount;
import io.methea.domain.view.AccountView;
import io.methea.repository.AccountRepository;
import io.methea.service.abs.AbstractSimpleMetheaService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Author : DKSilverX
 * Date : 19/01/2020
 */
@Service
public class AccountService extends AbstractSimpleMetheaService<TAccount, AccountBinder, String, AccountView> {

    @Inject
    public AccountService(AccountRepository accountRepository) {
        super(AccountView.class, accountRepository, accountRepository);
    }
}
