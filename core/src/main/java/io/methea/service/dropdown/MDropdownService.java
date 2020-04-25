package io.methea.service.dropdown;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.domain.configuration.account.dropdown.AccountDropdown;
import io.methea.domain.configuration.account.dropdown.GroupDropdown;
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
    private final Map<String, Object> param = new HashMap<>();

    @Inject
    public MDropdownService(HibernateExtensionRepository<AccountDropdown, String> repository,
                            HibernateExtensionRepository<GroupDropdown, String> groupRepository) {
        this.repository = repository;
        this.groupRepository = groupRepository;
        param.put(MConstant.JSON_STATUS, MConstant.ACTIVE_STATUS);
    }

    public void getDropdownData() {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put(MConstant.ACCOUNT_DROPDOWN, getAccountDropdown());
            map.put(MConstant.GROUP_DROPDOWN, getGroupDropdown());
            MCache.cacheMetaData.put(MConstant.DROPDOWN, map);
        } catch (Exception ex) {
            log.error(">>>>> Get dropdown data error: ", ex);
        }
    }

    private List<AccountDropdown> getAccountDropdown() {
        List<AccountDropdown> list = new ArrayList<>();
        try {
            list = repository.getByQuery(param, AccountDropdown.class);
        } catch (Exception ex) {
            log.error(">>>>> Get account dropdown error: ", ex);
        }
        return list;
    }

    private List<GroupDropdown> getGroupDropdown(){
        List<GroupDropdown> list = new ArrayList<>();
        try{
            list = groupRepository.getByQuery(param, GroupDropdown.class);
        }catch (Exception ex){
            log.error(">>>>> Get group dropdown error: ", ex);
        }
        return list;
    }
}
