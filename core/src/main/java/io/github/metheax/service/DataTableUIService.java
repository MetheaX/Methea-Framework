package io.github.metheax.service;

import io.github.metheax.cache.MetheaCache;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.domain.entity.TDataTableView;
import io.github.metheax.domain.binder.DataTableBinder;
import io.github.metheax.domain.view.DataTableView;
import io.github.metheax.repository.DisplayRepository;
import io.github.metheax.service.abs.AbstractSimpleMetheaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.util.*;

/**
 * Author : DKSilverX
 * Date : 19/01/2020
 */
@Service
public class DataTableUIService extends AbstractSimpleMetheaService<TDataTableView, DataTableBinder, String, DataTableView> {

    private static Logger log = LoggerFactory.getLogger(DataTableUIService.class);

    private final DisplayRepository displayRepository;

    @Inject
    public DataTableUIService(DisplayRepository displayRepository) {
        super(DataTableView.class, displayRepository, displayRepository);
        this.displayRepository = displayRepository;
    }

    public void getMetaTableConfiguration(String viewName) {
        List<String> columnLabels = new ArrayList<>();
        List<String> columnKeys = new ArrayList<>();
        Map<String, String> columnFilter = new LinkedHashMap<>();

        try {
            List<TDataTableView> dataTableViews = displayRepository
                    .findAllByViewNameAndStatusOrderBySequence(viewName, MetheaConstant.ACTIVE_STATUS);
            if (!CollectionUtils.isEmpty(dataTableViews)) {
                for (TDataTableView t : dataTableViews) {
                    columnLabels.add(t.getLabelColumnHead());
                    columnKeys.add(t.getColumnKey());
                    if (MetheaConstant.YES.equalsIgnoreCase(t.getAllowFilter())) {
                        columnFilter.put("filter_".concat(t.getColumnKey()), t.getLabelColumnHead());
                    }
                }
            }
            MetheaCache.CACHE_META_DATA.put(viewName.concat(MetheaConstant.COLUMNS_LABEL), columnLabels);
            MetheaCache.CACHE_META_DATA.put(viewName.concat(MetheaConstant.COLUMNS_KEY), columnKeys);
            MetheaCache.CACHE_META_DATA.put(viewName.concat(MetheaConstant.COLUMNS_FILTER), columnFilter);
        } catch (Exception ex) {
            log.error("=========> Get datatable meta data error: ", ex);
        }
    }
}
