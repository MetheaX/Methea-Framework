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
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
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
    private final MDropdownService dropdownService;

    @Inject
    public MUserController(DataTableUIService dataTableUIService, MUserService mUserService,
                           MDropdownService dropdownService) {
        super(dataTableUIService);
        metheaService = mUserService;
        entity = "users";
        configViewName = "userList";
        templatePath = "configuration/user/user-list";
        this.dropdownService = dropdownService;
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
    protected TUser getEntityFromBinder(UserBinder binder){
        TUser user = new TUser();
        user.setId(UUID.randomUUID().toString());
        return user;
    }

    @Override
    protected String getEntityId(UserBinder binder){
        return binder.getId();
    }
}
