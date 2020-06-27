package io.methea.service.configuration.display;

import io.methea.domain.configuration.display.dto.DataTableBinder;
import io.methea.domain.configuration.display.entity.TDataTableView;
import io.methea.domain.configuration.display.view.DataTableView;
import io.methea.repository.configuration.display.DisplayRepository;
import io.methea.service.abs.AbstractSimpleMetheaService;
import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 08/06/2020
 */
@Service
public class MDataTableService extends AbstractSimpleMetheaService<TDataTableView, DataTableBinder, String, DataTableView> {
    public MDataTableService(DisplayRepository displayRepository) {
        super(DataTableView.class, displayRepository, displayRepository);
    }
}
