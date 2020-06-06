package io.methea.service.dropdown;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.domain.configuration.account.dropdown.AccountDropdown;
import io.methea.domain.configuration.group.dropdown.GroupDropdown;
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
    private static Logger log = LoggerFactory.getLogger(MDropdownService.class);

    private final HibernateExtensionRepository<AccountDropdown, String> repository;
    private final HibernateExtensionRepository<GroupDropdown, String> groupRepository;
    private final HibernateExtensionRepository<RoleDropdown, String> roleRepository;
    private final HibernateExtensionRepository<URIDropdown, String> uriRepository;
    private final HibernateExtensionRepository<UserDropdown, String> userRepository;
    private final HibernateExtensionRepository<APIBaseDropdown, String> apiBaseRepository;
    private final Map<String, Object> param = new HashMap<>();

    @Inject
    public MDropdownService(HibernateExtensionRepository<AccountDropdown, String> repository,
                            HibernateExtensionRepository<GroupDropdown, String> groupRepository,
                            HibernateExtensionRepository<RoleDropdown, String> roleRepository,
                            HibernateExtensionRepository<URIDropdown, String> uriRepository,
                            HibernateExtensionRepository<UserDropdown, String> userRepository,
                            HibernateExtensionRepository<APIBaseDropdown, String> apiBaseRepository) {
        this.repository = repository;
        this.groupRepository = groupRepository;
        this.roleRepository = roleRepository;
        this.uriRepository = uriRepository;
        this.userRepository = userRepository;
        this.apiBaseRepository = apiBaseRepository;
        param.put(MConstant.JSON_STATUS, MConstant.ACTIVE_STATUS);
    }

    public void getDropdownData() {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put(MConstant.ACCOUNT_DROPDOWN, getAccountDropdown());
            map.put(MConstant.USER_DROPDOWN, getUserDropdown());
            map.put(MConstant.GROUP_DROPDOWN, getGroupDropdown());
            map.put(MConstant.ROLE_DROPDOWN, getRoleDropdown());
            map.put(MConstant.URI_DROPDOWN, getURIDropdown());
            map.put(MConstant.API_URL_DROPDOWN, getApiBaseDropdown());
            MCache.cacheMetaData.put(MConstant.DROPDOWN, map);
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

    private List<GroupDropdown> getGroupDropdown() {
        List<GroupDropdown> list = new ArrayList<>();
        try {
            list = groupRepository.getByQuery(param, GroupDropdown.class);
        } catch (Exception ex) {
            log.error("=========> Get group dropdown error: ", ex);
        }
        return list;
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

    private List<URIDropdown> getURIDropdown() {
        List<URIDropdown> list = new ArrayList<>();
        try {
            list = uriRepository.getByQuery(param, URIDropdown.class);
        } catch (Exception ex) {
            log.error("=========> Get URI dropdown error: ", ex);
        }
        return list;
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

    private List<APIBaseDropdown> getApiBaseDropdown() {
        List<APIBaseDropdown> list = new ArrayList<>();
        try {
            list = apiBaseRepository.getByQuery(param, APIBaseDropdown.class);
        } catch (Exception ex) {
            log.error("=========> Get api dropdown error: ", ex);
        }
        return list;
    }
}
