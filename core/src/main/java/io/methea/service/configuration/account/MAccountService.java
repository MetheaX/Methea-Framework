package io.methea.service.configuration.account;

import io.methea.domain.configuration.account.dto.AccountBinder;
import io.methea.domain.configuration.account.entity.TAccount;
import io.methea.domain.configuration.account.filter.AccountFilter;
import io.methea.domain.configuration.account.projection.AccountProjection;
import io.methea.repository.configuration.account.AccountRepository;
import io.methea.repository.hibernateextension.domain.HibernatePage;
import io.methea.util.Pagination;
import io.methea.util.PrincipalUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Author : DKSilverX
 * Date : 19/01/2020
 */
@Service
public class MAccountService {

    private static Logger log = LoggerFactory.getLogger(MAccountService.class);

    private final AccountRepository accountRepository;

    @Inject
    public MAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountBinder saveAccount(AccountBinder accountBinder, HttpServletRequest request) {
        TAccount account = new TAccount();
        try {
            account.setId(UUID.randomUUID().toString());
            account.setAccountName(accountBinder.getAccountName());
            account.setAccountEmail(accountBinder.getAccountEmail());
            account.setAccountAddress(accountBinder.getAccountAddress());
            account.setStatus("A");
            account.setCreatedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),
                    ZoneId.systemDefault()));
            account.setUpdatedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),
                    ZoneId.systemDefault()));
            account.setCreatedUser(PrincipalUtils.getUserLoginId(request));
            account.setUpdatedUser(PrincipalUtils.getUserLoginId(request));

            accountRepository.save(account);
            log.info(">>>>> Account[" + account.getAccountEmail() + "] saved.");
        } catch (Exception ex) {
            log.error(">>>>> Save account[" + accountBinder.getAccountEmail() + "] error: ", ex);
        }
        return accountBinder;
    }

    public AccountBinder modifyAccount(AccountBinder accountBinder, HttpServletRequest request) {
        try {
            TAccount account;
            Optional<TAccount> accountOptional = accountRepository.findById(accountBinder.getId());
            if (accountOptional.isPresent()) {
                account = accountOptional.get();
                account.setAccountName(accountBinder.getAccountName());
                account.setAccountEmail(accountBinder.getAccountEmail());
                account.setAccountAddress(accountBinder.getAccountAddress());
                account.setUpdatedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),
                        ZoneId.systemDefault()));
                account.setUpdatedUser(PrincipalUtils.getUserLoginId(request));

                accountRepository.save(account);
                log.info(">>>>> Account[" + account.getAccountEmail() + "] modified.");
            }
        } catch (Exception ex) {
            log.error(">>>>> Modify account[" + accountBinder.getAccountEmail() + "] error: ", ex);
        }
        return accountBinder;
    }

    public boolean activateAccount(String id, HttpServletRequest request) {
        boolean isActivate = false;
        try {
            Optional<TAccount> account = accountRepository.findById(id);
            if (account.isPresent()) {
                TAccount acc = account.get();
                acc.setStatus("A");
                acc.setUpdatedUser(PrincipalUtils.getUserLoginId(request));
                acc.setUpdatedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),
                        ZoneId.systemDefault()));
                accountRepository.save(acc);
                isActivate = true;
            }
        } catch (Exception ex) {
            log.error(">>>>> Activate account error : ", ex);
        }
        return isActivate;
    }

    public boolean deactivateAccount(String id, HttpServletRequest request) {
        boolean isDeactivate = false;
        try {
            Optional<TAccount> account = accountRepository.findById(id);
            if (account.isPresent()) {
                TAccount acc = account.get();
                acc.setStatus("I");
                acc.setUpdatedUser(PrincipalUtils.getUserLoginId(request));
                acc.setUpdatedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),
                        ZoneId.systemDefault()));
                accountRepository.save(acc);
                isDeactivate = true;
            }
        } catch (Exception ex) {
            log.error(">>>>> Deactivate account error : ", ex);
        }
        return isDeactivate;
    }

    public List<AccountProjection> getAllAccountsByFilter(AccountFilter filter, Pagination pagination) {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("accountName", "%".concat(filter.getAccountName().toLowerCase()).concat("%"));
        parameters.put("accountEmail", "%".concat(filter.getAccountEmail().toLowerCase()).concat("%"));
        parameters.put("status", "%".concat(StringUtils.isEmpty(filter.getStatus()) ? StringUtils.EMPTY :
                (filter.getStatus().substring(0, 1).toLowerCase())).concat("%"));

        HibernatePage<AccountProjection> accountHibernatePage = accountRepository.getByQuery(parameters, AccountProjection.class,
                pagination.getSize(), pagination.getOffSet());
        pagination.setTotalCounts(accountHibernatePage.getTotalCount());

        return accountHibernatePage.getContent();
    }

    public AccountBinder getAccountById(String id) {
        AccountBinder accountBinder = new AccountBinder();
        Optional<TAccount> accountOpt;
        try {
            accountOpt = accountRepository.findById(id);
            if (accountOpt.isPresent()) {
                TAccount account = accountOpt.get();
                accountBinder.setId(account.getId());
                accountBinder.setAccountName(account.getAccountName());
                accountBinder.setAccountEmail(account.getAccountEmail());
                accountBinder.setAccountAddress(account.getAccountAddress());
            }
        } catch (Exception ex) {
            log.error(">>>>> Get account by id error: ", ex);
        }
        return accountBinder;
    }
}
