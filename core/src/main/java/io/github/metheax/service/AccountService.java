package io.github.metheax.service;

import io.github.metheax.domain.entity.TAccount;
import io.github.metheax.domain.view.AccountView;
import io.github.metheax.repository.AccountRepository;
import io.github.metheax.domain.binder.AccountBinder;
import io.github.metheax.service.abs.AbstractSimpleMetheaService;
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
