package io.github.metheax.web.controller;

import io.github.metheax.controller.AbstractSimpleMetheaController;
import io.github.metheax.domain.entity.TAccount;
import io.github.metheax.domain.view.AccountView;
import io.github.metheax.service.AccountService;
import io.github.metheax.validator.AccountValidator;
import io.github.metheax.domain.binder.AccountBinder;
import io.github.metheax.domain.filter.AccountFilter;
import io.github.metheax.service.DataTableUIService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 1/3/2020
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping(value = {AccountController.ROOT_URL})
public class AccountController extends AbstractSimpleMetheaController<TAccount, AccountBinder, AccountView, AccountFilter> {

    static final String ROOT_URL = "/app/accounts";

    @Inject
    public AccountController(DataTableUIService dataTableUIService, AccountService accountService, AccountValidator validator) {
        super(dataTableUIService);
        super.validator = validator;
        super.simpleMetheaService = accountService;
        entity = "accounts";
        super.dataTableId = "tbl-accounts";
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
