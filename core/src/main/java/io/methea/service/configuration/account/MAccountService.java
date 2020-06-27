package io.methea.service.configuration.account;

import io.methea.domain.configuration.account.dto.AccountBinder;
import io.methea.domain.configuration.account.entity.TAccount;
import io.methea.domain.configuration.account.view.AccountView;
import io.methea.repository.configuration.account.AccountRepository;
import io.methea.service.abs.AbstractSimpleMetheaService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Author : DKSilverX
 * Date : 19/01/2020
 */
@Service
public class MAccountService extends AbstractSimpleMetheaService<TAccount, AccountBinder, String, AccountView> {

    @Inject
    public MAccountService(AccountRepository accountRepository) {
        super(AccountView.class, accountRepository, accountRepository);
    }
}
