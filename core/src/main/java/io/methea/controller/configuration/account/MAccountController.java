package io.methea.controller.configuration.account;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.controller.abs.MBaseController;
import io.methea.domain.configuration.account.dto.AccountBinder;
import io.methea.domain.configuration.account.filter.AccountFilter;
import io.methea.domain.configuration.account.view.AccountView;
import io.methea.service.configuration.account.MAccountService;
import io.methea.service.configuration.display.DataTableUIService;
import io.methea.util.Pagination;
import io.methea.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Author : DKSilverX
 * Date : 1/3/2020
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MAccountController extends MBaseController {

    private static Logger log = LoggerFactory.getLogger(MAccountController.class);

    private static final String GET_ALL_ACCOUNTS_URL = "/accounts";
    private static final String SAVE_ACCOUNT_URL = "/account/save";
    private static final String MODIFY_ACCOUNT_URL = "/account/modify";
    private static final String REDIRECT_URL = "redirect:/app/accounts";
    private static final String GET_ACCOUNT_BY_ID_URL = "/api/v1/accounts";
    private static final String ACTIVATED_ACCOUNT_URL = "/api/v1/accounts/activate";
    private static final String DEACTIVATED_ACCOUNT_URL = "/api/v1/accounts/deactivate";

    private static final String ACCOUNTS_TEMPLATE_PATH = "configuration/account/account-list";

    private static final String VIEW_NAME = "accountList";

    private final DataTableUIService dataTableUIService;
    private final MAccountService mAccountService;

    @Inject
    public MAccountController(DataTableUIService dataTableUIService, MAccountService mAccountService) {
        this.dataTableUIService = dataTableUIService;
        this.mAccountService = mAccountService;
    }

    @RequestMapping(value = {GET_ALL_ACCOUNTS_URL})
    public String viewAccountList(Model model, AccountFilter filter, Pagination pagination, HttpServletRequest request) {

        //noinspection unchecked
        if (CollectionUtils.isEmpty((List<String>) MCache.cacheMetaData.get(VIEW_NAME.concat(MConstant.COLUMNS_KEY)))
                || CollectionUtils.isEmpty((List<String>) MCache.cacheMetaData.get(VIEW_NAME.concat(MConstant.COLUMNS_LABEL)))) {
            dataTableUIService.getMetaTableConfiguration(VIEW_NAME);
            log.info(">>>>> Fetch meta data of account's datatable.");
        }

        Map<String, List<AccountView>> mapBinder = new HashMap<>();
        mapBinder.put("data", mAccountService.getAllAccountsByFilter(filter, pagination));

        model.addAttribute("contextPath", SystemUtils.getBaseUrl(request));
        model.addAttribute("tableHead", MCache.cacheMetaData.get(VIEW_NAME.concat(MConstant.COLUMNS_LABEL)));
        model.addAttribute("tableColumns", MCache.cacheMetaData.get(VIEW_NAME.concat(MConstant.COLUMNS_KEY)));
        model.addAttribute("tableFilterColumns", MCache.cacheMetaData.get(VIEW_NAME.concat(MConstant.COLUMNS_FILTER)));
        model.addAttribute("accounts", mapBinder);
        model.addAttribute("dataFilters", filter);
        model.addAttribute("pagination", pagination);
        return ACCOUNTS_TEMPLATE_PATH;
    }

    @RequestMapping(value = {SAVE_ACCOUNT_URL})
    public ModelAndView saveAccount(AccountBinder accountBinder, HttpServletRequest request) {
        mAccountService.saveAccount(accountBinder, request);
        return new ModelAndView(REDIRECT_URL);
    }

    @RequestMapping(value = {MODIFY_ACCOUNT_URL})
    public ModelAndView modifyAccount(AccountBinder accountBinder, HttpServletRequest request) {
        mAccountService.modifyAccount(accountBinder, request);
        return new ModelAndView(REDIRECT_URL);
    }

    @ResponseBody
    @GetMapping(value = {GET_ACCOUNT_BY_ID_URL})
    public ResponseEntity<Map<String, Object>> getAccountById(@RequestParam String id) {
        Map<String, Object> map = new HashMap<>();
        AccountBinder binder;
        map.put("status", 500);
        map.put("message", "Failed to get account");
        binder = mAccountService.getAccountById(id);
        if (!ObjectUtils.isEmpty(binder)) {
            map.put("status", 200);
            map.put("account", binder);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = {ACTIVATED_ACCOUNT_URL})
    public ResponseEntity<Map<String, Object>> activateAccount(@RequestBody Map<String, String> payload,
                                                               HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 500);
        map.put("message", "Failed to activate account");
        if (mAccountService.activateAccount(payload.get("id"), request)) {
            map.put("status", 200);
            map.put("message", "Activate account successfully");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = {DEACTIVATED_ACCOUNT_URL})
    public ResponseEntity<Map<String, Object>> deactivateAccount(@RequestBody Map<String, String> payload,
                                                                 HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 500);
        map.put("message", "Failed to deactivate account");
        if (mAccountService.deactivateAccount(payload.get("id"), request)) {
            map.put("status", 200);
            map.put("message", "Deactivate account successfully");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
