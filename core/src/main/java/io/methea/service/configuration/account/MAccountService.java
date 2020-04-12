package io.methea.service.configuration.account;

import io.methea.domain.configuration.account.dto.AccountBinder;
import io.methea.domain.configuration.account.entity.TAccount;
import io.methea.repository.configuration.account.AccountRepository;
import io.methea.util.MDateUtil;
import io.methea.util.PrincipleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
            account.setCreatedUser(PrincipleUtils.getUserLoginId(request));
            account.setUpdatedUser(PrincipleUtils.getUserLoginId(request));

            accountRepository.save(account);
            log.info(">>>>> Account[" + account.getAccountEmail() + "] saved.");
        } catch (Exception ex) {
            log.error(">>>>> Save account[" + accountBinder.getAccountEmail() + "] error: ", ex);
        }
        return accountBinder;
    }

    public AccountBinder modifyAccount(AccountBinder accountBinder, HttpServletRequest request){
        try{
            TAccount account;
            Optional<TAccount> accountOptional = accountRepository.findById(accountBinder.getId());
            if(accountOptional.isPresent()){
                account = accountOptional.get();
                account.setAccountName(accountBinder.getAccountName());
                account.setAccountEmail(accountBinder.getAccountEmail());
                account.setAccountAddress(accountBinder.getAccountAddress());
                account.setUpdatedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),
                        ZoneId.systemDefault()));
                account.setUpdatedUser(PrincipleUtils.getUserLoginId(request));

                accountRepository.save(account);
                log.info(">>>>> Account[" + account.getAccountEmail() + "] modified.");
            }
        }catch (Exception ex){
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
                acc.setUpdatedUser(PrincipleUtils.getUserLoginId(request));
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
                acc.setUpdatedUser(PrincipleUtils.getUserLoginId(request));
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

    public List<AccountBinder> getAllAccounts() {
        List<AccountBinder> accountBinders = new ArrayList<>();
        List<TAccount> accounts;
        AccountBinder accountBinder;
        try {
            accounts = (List<TAccount>) accountRepository.findAllByOrderByStatusAsc();
            if (!CollectionUtils.isEmpty(accounts)) {
                for (TAccount account : accounts) {
                    accountBinder = new AccountBinder();
                    accountBinder.setId(account.getId());
                    accountBinder.setAccountName(account.getAccountName());
                    accountBinder.setAccountEmail(account.getAccountEmail());
                    accountBinder.setAccountAddress(account.getAccountAddress());
                    accountBinder.setAccountUpdatedUser(account.getUpdatedUser());
                    accountBinder.setAccountCreatedUser(account.getCreatedUser());
                    accountBinder.setAccountCreatedDate(MDateUtil.convertDateToString(account.getCreatedDateTime()));
                    accountBinder.setAccountUpdatedDate(MDateUtil.convertDateToString(account.getUpdatedDateTime()));
                    accountBinder.setStatus(account.getStatus());

                    accountBinders.add(accountBinder);
                }
            }
        } catch (Exception ex) {
            log.error(">>>>> Get all accounts error: ", ex);
        }
        return accountBinders;
    }

    public AccountBinder getAccountById(String id){
        AccountBinder accountBinder = new AccountBinder();
        Optional<TAccount> accountOpt;
        try{
            accountOpt = accountRepository.findById(id);
            if(accountOpt.isPresent()){
                TAccount account = accountOpt.get();
                accountBinder.setId(account.getId());
                accountBinder.setAccountName(account.getAccountName());
                accountBinder.setAccountEmail(account.getAccountEmail());
                accountBinder.setAccountAddress(account.getAccountAddress());
            }
        }catch (Exception ex){
            log.error(">>>>> Get account by id error: ", ex);
        }
        return accountBinder;
    }
}
