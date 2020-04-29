package io.methea.controller.configuration.user;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.controller.abs.AbstractMetheaController;
import io.methea.domain.configuration.user.dto.UserBinder;
import io.methea.domain.configuration.user.entity.TUser;
import io.methea.domain.configuration.user.filter.UserFilter;
import io.methea.domain.configuration.user.view.UserView;
import io.methea.service.configuration.display.DataTableUIService;
import io.methea.service.configuration.user.MUserService;
import io.methea.service.dropdown.MDropdownService;
import io.methea.util.Pagination;
import io.methea.validator.configuration.user.UserValidator;
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
@RequestMapping(value = MUserController.ROOT_URL)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MUserController extends AbstractMetheaController<TUser, UserBinder, UserView, UserFilter> {

    static final String ROOT_URL = "/app/users";
    private static final String RESET_PASSWORD = "/reset-password";
    private final MDropdownService dropdownService;
    private final MUserService service;

    @Inject
    public MUserController(DataTableUIService dataTableUIService, MUserService mUserService,
                           MDropdownService dropdownService, UserValidator userValidator) {
        super(dataTableUIService);
        super.validator = userValidator;
        super.metheaService = mUserService;
        super.entity = "users";
        super.dataTableId = "tbl-users";
        super.configViewName = "userList";
        super.templatePath = "configuration/user/user-list";
        this.dropdownService = dropdownService;
        this.service = mUserService;
    }

    @Override
    protected UserFilter initFilter() {
        return new UserFilter();
    }

    @Override
    protected Model getExtraAttribute(Model model) {
        if (CollectionUtils.isEmpty((Map<?, ?>) MCache.cacheMetaData.get(MConstant.DROPDOWN))) {
            dropdownService.getDropdownData();
        }
        model.addAttribute(MConstant.DROPDOWN, MCache.cacheMetaData.get(MConstant.DROPDOWN));
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
        errors.put("validatePassword", "true");
        validator.validate(binder, errors);
        dataTableAttributes(model, binder, initFilter(), pagination, request);
        model.addAttribute("popup", dataTableId.concat("-reset-password"));
        if (!CollectionUtils.isEmpty(errors)) {
            model.addAttribute("hasErrors", true);
            model.addAttribute("errors", errors);
            return new ModelAndView(templatePath);
        }
        service.resetUserPassword(binder.getId(), binder);
        return new ModelAndView("redirect:" + AbstractMetheaController.ROOT_URL.concat(MConstant.SLASH).concat(entity));
    }
}
