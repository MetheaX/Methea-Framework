package io.methea.service.configuration.display;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.domain.configuration.display.entity.TDataTableView;
import io.methea.repository.configuration.display.DisplayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Author : DKSilverX
 * Date : 19/01/2020
 */
@Service
public class DataTableUIService {

    private static Logger log = LoggerFactory.getLogger(DataTableUIService.class);

    private final DisplayRepository displayRepository;

    @Inject
    public DataTableUIService(DisplayRepository displayRepository) {
        this.displayRepository = displayRepository;
    }

    public void getMetaTableConfiguration(String viewName) {
        List<String> columnLabels = new ArrayList<>();
        List<String> columnKeys = new ArrayList<>();

        try {
            List<TDataTableView> dataTableViews = displayRepository
                    .findAllByViewNameAndStatusOrderBySequence(viewName, "A");
            if (!CollectionUtils.isEmpty(dataTableViews)) {
                for (TDataTableView t : dataTableViews) {
                    columnLabels.add(t.getLabelColumnHead());
                    columnKeys.add(t.getColumnKey());
                }
            }
            MCache.cacheMetaData.put(MConstant.ACCOUNT_LIST_COLUMNS_LABEL, columnLabels);
            MCache.cacheMetaData.put(MConstant.ACCOUNT_LIST_COLUMNS_KEY, columnKeys);
        } catch (Exception ex) {
            log.error(">>>>> Get metadata of account's datatable error: ", ex);
        }
    }
}
