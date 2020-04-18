package io.methea.service.configuration.account;

import io.methea.domain.configuration.account.dto.AccountBinder;
import io.methea.domain.configuration.account.entity.TAccount;
import io.methea.domain.configuration.account.view.AccountView;
import io.methea.repository.configuration.account.AccountRepository;
import io.methea.service.abs.AbstractMetheaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Author : DKSilverX
 * Date : 19/01/2020
 */
@Service
public class MAccountService extends AbstractMetheaService<TAccount, AccountBinder, String, AccountView> {

    private static Logger log = LoggerFactory.getLogger(MAccountService.class);

    @Inject
    public MAccountService(AccountRepository accountRepository) {
        super(AccountView.class, accountRepository, accountRepository);
    }
}
