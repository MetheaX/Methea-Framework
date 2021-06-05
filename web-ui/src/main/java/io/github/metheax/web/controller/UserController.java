package io.github.metheax.web.controller;

import io.github.metheax.cache.MetheaCache;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.controller.AbstractSimpleMetheaController;
import io.github.metheax.service.DataTableUIService;
import io.github.metheax.service.DropdownService;
import io.github.metheax.validator.UserValidator;
import io.github.metheax.domain.binder.UserBinder;
import io.github.metheax.domain.entity.TUser;
import io.github.metheax.domain.filter.UserFilter;
import io.github.metheax.domain.view.UserView;
import io.github.metheax.service.UserService;
import io.github.metheax.utils.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 23/04/2020
 */
@Controller
@RequestMapping(value = UserController.ROOT_URL)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserController extends AbstractSimpleMetheaController<TUser, UserBinder, UserView, UserFilter> {

    static final String ROOT_URL = "/app/users";
    private static final String RESET_PASSWORD = "/reset-password";
    private final DropdownService dropdownService;
    private final UserService service;

    @Inject
    public UserController(DataTableUIService dataTableUIService, UserService userService,
                          DropdownService dropdownService, UserValidator userValidator) {
        super(dataTableUIService);
        super.validator = userValidator;
        super.simpleMetheaService = userService;
        super.entity = "users";
        super.dataTableId = "tbl-users";
        super.configViewName = "userList";
        super.templatePath = "configuration/user/user-list";
        this.dropdownService = dropdownService;
        this.service = userService;
    }

    @Override
    protected UserFilter initFilter() {
        return new UserFilter();
    }

    @Override
    protected Model getExtraAttribute(Model model) {
        dropdownService.refreshGroupDropdown();
        model.addAttribute(MetheaConstant.DROPDOWN, MetheaCache.CACHE_META_DATA.get(MetheaConstant.DROPDOWN));
        return model;
    }

    @Override
    protected Map<String, Object> getFilterColumns(UserFilter filter) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", "%".concat(filter.getUsername().toLowerCase()).concat("%"));
        params.put("groupName", "%".concat(filter.getGroupName().toLowerCase()).concat("%"));
        params.put("firstName", "%".concat(filter.getFirstName().toLowerCase()).concat("%"));
        params.put("lastName", "%".concat(filter.getLastName().toLowerCase()).concat("%"));
        params.put("phone", "%".concat(filter.getPhone().toLowerCase()).concat("%"));
        params.put("email", "%".concat(filter.getEmail().toLowerCase()).concat("%"));
        params.put("status", "%".concat(StringUtils.isEmpty(filter.getStatus()) ? StringUtils.EMPTY :
                (filter.getStatus().substring(0, 1).toLowerCase())).concat("%"));
        return params;
    }

    @Override
    protected TUser getEntityFromBinder(UserBinder binder) {
        TUser user = new TUser();
        user.setId(UUID.randomUUID().toString());
        user.setForceUserResetPassword("Y");
        return user;
    }

    @Override
    protected String getEntityId(UserBinder binder) {
        return binder.getId();
    }

    @RequestMapping(value = RESET_PASSWORD, method = RequestMethod.POST)
    public ModelAndView resetUserPassword(UserBinder binder, Model model, UserFilter filter, Pagination pagination,
                                          HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("validateType", "RST");
        validator.validate(binder, errors);
        dataTableAttributes(model, binder, initFilter(), pagination, request);
        model.addAttribute("popup", dataTableId.concat("-reset-password"));
        if (!CollectionUtils.isEmpty(errors)) {
            model.addAttribute("hasErrors", true);
            model.addAttribute("errors", errors);
            return new ModelAndView(templatePath);
        }
        binder.setForceUserResetPassword("Y");
        service.resetUserPassword(binder.getId(), binder);
        return new ModelAndView("redirect:" + AbstractSimpleMetheaController.ROOT_URL.concat(MetheaConstant.SLASH).concat(entity));
    }
}
