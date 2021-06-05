package io.github.metheax.service;

import io.github.metheax.domain.entity.TDataTableView;
import io.github.metheax.domain.binder.DataTableBinder;
import io.github.metheax.domain.view.DataTableView;
import io.github.metheax.repository.DisplayRepository;
import io.github.metheax.service.abs.AbstractSimpleMetheaService;
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
