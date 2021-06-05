package io.github.metheax.service;

import io.github.metheax.cache.MetheaCache;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.domain.dropdown.AccountDropdown;
import io.github.metheax.domain.dropdown.GroupDropdown;
import io.github.metheax.domain.dropdown.MenuDropdown;
import io.github.metheax.domain.dropdown.RoleDropdown;
import io.github.metheax.domain.binder.URIDropdown;
import io.github.metheax.domain.dropdown.UserDropdown;
import io.github.metheax.domain.dropdown.APIBaseDropdown;
import io.github.metheax.repository.hibernateextension.HibernateExtensionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 17/04/2020
 */
@Service
public class DropdownService {
    private static final Logger log = LoggerFactory.getLogger(DropdownService.class);

    private final HibernateExtensionRepository<AccountDropdown, String> repository;
    private final HibernateExtensionRepository<GroupDropdown, String> groupRepository;
    private final HibernateExtensionRepository<RoleDropdown, String> roleRepository;
    private final HibernateExtensionRepository<URIDropdown, String> uriRepository;
    private final HibernateExtensionRepository<UserDropdown, String> userRepository;
    private final HibernateExtensionRepository<APIBaseDropdown, String> apiBaseRepository;
    private final HibernateExtensionRepository<MenuDropdown, String> menuRepository;
    private final Map<String, Object> param = new HashMap<>();
    private final Map<String, Object> dropdown = new HashMap<>();

    @Inject
    public DropdownService(HibernateExtensionRepository<AccountDropdown, String> repository,
                           HibernateExtensionRepository<GroupDropdown, String> groupRepository,
                           HibernateExtensionRepository<RoleDropdown, String> roleRepository,
                           HibernateExtensionRepository<URIDropdown, String> uriRepository,
                           HibernateExtensionRepository<UserDropdown, String> userRepository,
                           HibernateExtensionRepository<APIBaseDropdown, String> apiBaseRepository,
                           HibernateExtensionRepository<MenuDropdown, String> menuRepository) {
        this.repository = repository;
        this.groupRepository = groupRepository;
        this.roleRepository = roleRepository;
        this.uriRepository = uriRepository;
        this.userRepository = userRepository;
        this.apiBaseRepository = apiBaseRepository;
        this.menuRepository = menuRepository;
        param.put(MetheaConstant.JSON_STATUS, MetheaConstant.ACTIVE_STATUS);

        try {
            dropdown.put(MetheaConstant.YES_NO_DROPDOWN, getYesNoDropdown());
            dropdown.put(MetheaConstant.ACCOUNT_DROPDOWN, getAccountDropdown());
            dropdown.put(MetheaConstant.USER_DROPDOWN, getUserDropdown());
            dropdown.put(MetheaConstant.GROUP_DROPDOWN, getGroupDropdown());
            dropdown.put(MetheaConstant.ROLE_DROPDOWN, getRoleDropdown());
            dropdown.put(MetheaConstant.URI_DROPDOWN, getURIDropdown());
            dropdown.put(MetheaConstant.MENU_DROPDOWN, getMenuDropdown());
            dropdown.put(MetheaConstant.HTTP_METHOD_DROPDOWN, getHttpMethodDropdown());
            dropdown.put(MetheaConstant.API_URL_DROPDOWN, getApiBaseDropdown());
            MetheaCache.CACHE_META_DATA.put(MetheaConstant.DROPDOWN, dropdown);
        } catch (Exception ex) {
            log.error("=========> Get dropdown data error: ", ex);
        }
    }

    private List<AccountDropdown> getAccountDropdown() {
        List<AccountDropdown> list = new ArrayList<>();
//        try {
//            list = repository.getByQuery(param, AccountDropdown.class);
//        } catch (Exception ex) {
//            log.error("=========> Get account dropdown error: ", ex);
//        }
        return list;
    }

    public void refreshAccountDropdown() {
        dropdown.put(MetheaConstant.ACCOUNT_DROPDOWN, getAccountDropdown());
    }

    private List<GroupDropdown> getGroupDropdown() {
        List<GroupDropdown> list = new ArrayList<>();
//        try {
//            Map<String, Object> param = new HashMap<>();
//            param.put(MConstant.JSON_STATUS, MConstant.ACTIVE_STATUS);
//            param.put("accountId", "%%");
//            list = groupRepository.getByQuery(param, GroupDropdown.class);
//        } catch (Exception ex) {
//            log.error("=========> Get getGroupDropdown error: ", ex);
//        }
        return list;
    }

    public List<GroupDropdown> getGroupDropdownByAccount(String accountId) {
//        try {
//            Map<String, Object> param = new HashMap<>();
//            param.put(MConstant.JSON_STATUS, MConstant.ACTIVE_STATUS);
//            param.put("accountId", "%".concat(accountId).concat("%"));
//            return groupRepository.getByQuery(param, GroupDropdown.class);
//        } catch (Exception ex) {
//            log.error("=========> Get getGroupDropdownByAccount error: ", ex);
//        }
        return null;
    }

    public void refreshGroupDropdown() {
        dropdown.put(MetheaConstant.GROUP_DROPDOWN, getGroupDropdown());
    }

    private List<RoleDropdown> getRoleDropdown() {
        List<RoleDropdown> list = new ArrayList<>();
//        try {
//            list = roleRepository.getByQuery(param, RoleDropdown.class);
//        } catch (Exception ex) {
//            log.error("=========> Get role dropdown error: ", ex);
//        }
        return list;
    }

    public void refreshRoleDropdown() {
        dropdown.put(MetheaConstant.ROLE_DROPDOWN, getRoleDropdown());
    }

    private List<URIDropdown> getURIDropdown() {
        List<URIDropdown> list = new ArrayList<>();
//        try {
//            list = uriRepository.getByQuery(param, URIDropdown.class);
//        } catch (Exception ex) {
//            log.error("=========> Get URI dropdown error: ", ex);
//        }
        return list;
    }

    public void refreshURIDropdown() {
        dropdown.put(MetheaConstant.URI_DROPDOWN, getURIDropdown());
    }

    private List<UserDropdown> getUserDropdown() {
        List<UserDropdown> list = new ArrayList<>();
//        try {
//            list = userRepository.getByQuery(param, UserDropdown.class);
//        } catch (Exception ex) {
//            log.error("=========> Get user dropdown error: ", ex);
//        }
        return list;
    }

    public void refreshUserDropdown() {
        dropdown.put(MetheaConstant.USER_DROPDOWN, getUserDropdown());
    }

    private List<APIBaseDropdown> getApiBaseDropdown() {
        List<APIBaseDropdown> list = new ArrayList<>();
//        try {
//            list = apiBaseRepository.getByQuery(param, APIBaseDropdown.class);
//        } catch (Exception ex) {
//            log.error("=========> Get api dropdown error: ", ex);
//        }
        return list;
    }

    private List<MenuDropdown> getMenuDropdown() {
        List<MenuDropdown> list = new ArrayList<>();
        MenuDropdown defaultMenu = new MenuDropdown("P", "None");
//        try {
//            list.add(defaultMenu);
//            list.addAll(menuRepository.getByQuery(param, MenuDropdown.class));
//        } catch (Exception ex) {
//            log.error("=========> Get menu dropdown error: ", ex);
//        }
        return list;
    }

    public void refreshMenuDropdown() {
        dropdown.put(MetheaConstant.MENU_DROPDOWN, getMenuDropdown());
    }

    private List<GenericDropdown> getYesNoDropdown() {
        return new ArrayList<GenericDropdown>() {
            private static final long serialVersionUID = -5416522703079609273L;

            {
                add(new GenericDropdown(MetheaConstant.YES, "Yes"));
                add(new GenericDropdown(MetheaConstant.NO, "No"));
            }
        };
    }

    private List<GenericDropdown> getHttpMethodDropdown() {
        return new ArrayList<GenericDropdown>() {
            private static final long serialVersionUID = -1763502692588341568L;

            {
                add(new GenericDropdown(MetheaConstant.POST, MetheaConstant.POST));
                add(new GenericDropdown(MetheaConstant.GET, MetheaConstant.GET));
                add(new GenericDropdown(MetheaConstant.PUT, MetheaConstant.PUT));
                add(new GenericDropdown(MetheaConstant.PATCH, MetheaConstant.PATCH));
                add(new GenericDropdown(MetheaConstant.DELETE, MetheaConstant.DELETE));
            }
        };
    }

    public class GenericDropdown {
        private String id;
        private String name;

        public GenericDropdown(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
