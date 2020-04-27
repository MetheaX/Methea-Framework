package io.methea.controller.configuration.account;

import io.methea.controller.abs.AbstractMetheaController;
import io.methea.domain.configuration.account.dto.AccountBinder;
import io.methea.domain.configuration.account.entity.TAccount;
import io.methea.domain.configuration.account.filter.AccountFilter;
import io.methea.domain.configuration.account.view.AccountView;
import io.methea.service.configuration.account.MAccountService;
import io.methea.service.configuration.display.DataTableUIService;
import io.methea.validator.configuration.account.AccountValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.*;

/**
 * Author : DKSilverX
 * Date : 1/3/2020
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping(value = {MAccountController.ROOT_URL})
public class MAccountController extends AbstractMetheaController<TAccount, AccountBinder, AccountView, AccountFilter> {

    static final String ROOT_URL = "/app/accounts";

    @Inject
    public MAccountController(DataTableUIService dataTableUIService, MAccountService mAccountService, AccountValidator validator) {
        super(dataTableUIService);
        super.validator = validator;
        super.metheaService = mAccountService;
        entity = "accounts";
        configViewName = "accountList";
        templatePath = "configuration/account/account-list";
    }

    @Override
    protected AccountFilter initFilter() {
        return new AccountFilter();
    }

    @Override
    protected Map<String, Object> getFilterColumns(AccountFilter filter) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("accountName", "%".concat(filter.getAccountName().toLowerCase()).concat("%"));
        parameters.put("accountEmail", "%".concat(filter.getAccountEmail().toLowerCase()).concat("%"));
        parameters.put("status", "%".concat(StringUtils.isEmpty(filter.getStatus()) ? StringUtils.EMPTY :
                (filter.getStatus().substring(0, 1).toLowerCase())).concat("%"));
        return parameters;
    }

    @Override
    protected TAccount getEntityFromBinder(AccountBinder binder) {
        TAccount account = new TAccount();
        account.setId(UUID.randomUUID().toString());
        return account;
    }

    @Override
    protected String getEntityId(AccountBinder binder) {
        return binder.getId();
    }
}
