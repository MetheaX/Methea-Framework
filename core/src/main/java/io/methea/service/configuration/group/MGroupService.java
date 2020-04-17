package io.methea.service.configuration.group;

import io.methea.domain.configuration.group.filter.GroupFilter;
import io.methea.domain.configuration.group.view.GroupView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import io.methea.repository.hibernateextension.domain.HibernatePage;
import io.methea.util.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 16/04/2020
 */
@Service
public class MGroupService {

    private final HibernateExtensionRepository<GroupView> repository;

    @Inject
    public MGroupService(HibernateExtensionRepository<GroupView> repository) {
        this.repository = repository;
    }

    public List<GroupView> getAllGroupsByFilter(GroupFilter filter, Pagination pagination) {
        Map<String, Object> params = new HashMap<>();

        params.put("groupName", "%".concat(filter.getGroupName()).concat("%"));
        params.put("accountName", "%".concat(filter.getAccountName().toLowerCase()).concat("%"));
        params.put("status", "%".concat(StringUtils.isEmpty(filter.getStatus()) ? StringUtils.EMPTY :
                (filter.getStatus().substring(0, 1).toLowerCase())).concat("%"));

        HibernatePage<GroupView> page = repository.getByQuery(params, GroupView.class, pagination.getSize(),
                pagination.getOffSet());
        pagination.setTotalCounts(page.getTotalCount());
        return page.getContent();
    }
}
