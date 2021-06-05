package io.methea.service;

import io.methea.domain.binder.DataTableBinder;
import io.methea.domain.entity.TDataTableView;
import io.methea.domain.view.DataTableView;
import io.methea.repository.DisplayRepository;
import io.methea.service.abs.AbstractSimpleMetheaService;
import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 08/06/2020
 */
@Service
public class DataTableService extends AbstractSimpleMetheaService<TDataTableView, DataTableBinder, String, DataTableView> {
    public DataTableService(DisplayRepository displayRepository) {
        super(DataTableView.class, displayRepository, displayRepository);
    }
}
