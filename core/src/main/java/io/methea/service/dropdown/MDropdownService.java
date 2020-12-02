package io.methea.service.dropdown;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.domain.configuration.account.dropdown.AccountDropdown;
import io.methea.domain.configuration.group.dropdown.GroupDropdown;
import io.methea.domain.configuration.menu.dropdown.MenuDropdown;
import io.methea.domain.configuration.role.dropdown.RoleDropdown;
import io.methea.domain.configuration.uri.dropdown.URIDropdown;
import io.methea.domain.configuration.user.dropdown.UserDropdown;
import io.methea.domain.webservice.baseapi.dropdown.APIBaseDropdown;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
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
public class MDropdownService {
    private static final Logger log = LoggerFactory.getLogger(MDropdownService.class);

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
    public MDropdownService(HibernateExtensionRepository<AccountDropdown, String> repository,
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
        param.put(MConstant.JSON_STATUS, MConstant.ACTIVE_STATUS);

        try {
            dropdown.put(MConstant.YES_NO_DROPDOWN, getYesNoDropdown());
            dropdown.put(MConstant.ACCOUNT_DROPDOWN, getAccountDropdown());
            dropdown.put(MConstant.USER_DROPDOWN, getUserDropdown());
            dropdown.put(MConstant.GROUP_DROPDOWN, getGroupDropdown());
            dropdown.put(MConstant.ROLE_DROPDOWN, getRoleDropdown());
            dropdown.put(MConstant.URI_DROPDOWN, getURIDropdown());
            dropdown.put(MConstant.MENU_DROPDOWN, getMenuDropdown());
            dropdown.put(MConstant.HTTP_METHOD_DROPDOWN, getHttpMethodDropdown());
            MCache.CACHE_META_DATA.put(MConstant.DROPDOWN, dropdown);
        } catch (Exception ex) {
            log.error("=========> Get dropdown data error: ", ex);
        }
    }

    private List<AccountDropdown> getAccountDropdown() {
        List<AccountDropdown> list = new ArrayList<>();
        try {
            list = repository.getByQuery(param, AccountDropdown.class);
        } catch (Exception ex) {
            log.error("=========> Get account dropdown error: ", ex);
        }
        return list;
    }

    public void refreshAccountDropdown() {
        dropdown.put(MConstant.ACCOUNT_DROPDOWN, getAccountDropdown());
    }

    private List<GroupDropdown> getGroupDropdown() {
        List<GroupDropdown> list = new ArrayList<>();
        try {
            list = groupRepository.getByQuery(param, GroupDropdown.class);
        } catch (Exception ex) {
            log.error("=========> Get group dropdown error: ", ex);
        }
        return list;
    }

    public void refreshGroupDropdown() {
        dropdown.put(MConstant.GROUP_DROPDOWN, getGroupDropdown());
    }

    private List<RoleDropdown> getRoleDropdown() {
        List<RoleDropdown> list = new ArrayList<>();
        try {
            list = roleRepository.getByQuery(param, RoleDropdown.class);
        } catch (Exception ex) {
            log.error("=========> Get role dropdown error: ", ex);
        }
        return list;
    }

    public void refreshRoleDropdown() {
        dropdown.put(MConstant.ROLE_DROPDOWN, getRoleDropdown());
    }

    private List<URIDropdown> getURIDropdown() {
        List<URIDropdown> list = new ArrayList<>();
        try {
            list = uriRepository.getByQuery(param, URIDropdown.class);
        } catch (Exception ex) {
            log.error("=========> Get URI dropdown error: ", ex);
        }
        return list;
    }

    public void refreshURIDropdown() {
        dropdown.put(MConstant.URI_DROPDOWN, getURIDropdown());
    }

    private List<UserDropdown> getUserDropdown() {
        List<UserDropdown> list = new ArrayList<>();
        try {
            list = userRepository.getByQuery(param, UserDropdown.class);
        } catch (Exception ex) {
            log.error("=========> Get user dropdown error: ", ex);
        }
        return list;
    }

    public void refreshUserDropdown() {
        dropdown.put(MConstant.USER_DROPDOWN, getUserDropdown());
    }

    private List<APIBaseDropdown> getApiBaseDropdown() {
        List<APIBaseDropdown> list = new ArrayList<>();
        try {
            list = apiBaseRepository.getByQuery(param, APIBaseDropdown.class);
        } catch (Exception ex) {
            log.error("=========> Get api dropdown error: ", ex);
        }
        return list;
    }

    private List<MenuDropdown> getMenuDropdown() {
        List<MenuDropdown> list = new ArrayList<>();
        MenuDropdown defaultMenu = new MenuDropdown("P", "None");
        try {
            list.add(defaultMenu);
            list.addAll(menuRepository.getByQuery(param, MenuDropdown.class));
        } catch (Exception ex) {
            log.error("=========> Get menu dropdown error: ", ex);
        }
        return list;
    }

    public void refreshMenuDropdown() {
        dropdown.put(MConstant.MENU_DROPDOWN, getMenuDropdown());
    }

    private List<GenericDropdown> getYesNoDropdown() {
        return new ArrayList<GenericDropdown>() {
            private static final long serialVersionUID = -5416522703079609273L;

            {
                add(new GenericDropdown(MConstant.YES, "Yes"));
                add(new GenericDropdown(MConstant.NO, "No"));
            }
        };
    }

    private List<GenericDropdown> getHttpMethodDropdown() {
        return new ArrayList<GenericDropdown>() {
            private static final long serialVersionUID = -1763502692588341568L;

            {
                add(new GenericDropdown(MConstant.POST, MConstant.POST));
                add(new GenericDropdown(MConstant.GET, MConstant.GET));
                add(new GenericDropdown(MConstant.PUT, MConstant.PUT));
                add(new GenericDropdown(MConstant.PATCH, MConstant.PATCH));
                add(new GenericDropdown(MConstant.DELETE, MConstant.DELETE));
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
