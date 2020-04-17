package io.methea.service.dropdown;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.domain.configuration.account.dropdown.AccountDropdown;
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

    @Inject
    public MDropdownService(HibernateExtensionRepository<AccountDropdown, String> repository) {
        this.repository = repository;
    }

    public void getDropdownData() {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put(MConstant.ACCOUNT_DROPDOWN, getAccountDropdown());
            MCache.cacheMetaData.put(MConstant.DROPDOWN, map);
        } catch (Exception ex) {
            log.error(">>>>> Get dropdown data error: ", ex);
        }
    }

    private List<AccountDropdown> getAccountDropdown() {
        List<AccountDropdown> list = new ArrayList<>();
        try {
            list = repository.getByQuery(new HashMap<>(), AccountDropdown.class);
        } catch (Exception ex) {
            log.error(">>>>> Get account dropdown error: ", ex);
        }
        return list;
    }
}
