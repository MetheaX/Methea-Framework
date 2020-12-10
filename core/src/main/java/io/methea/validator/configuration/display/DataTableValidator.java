package io.methea.validator.configuration.display;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.domain.configuration.display.dto.DataTableBinder;
import io.methea.validator.abs.AbstractMetheaValidator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 08/06/2020
 */
@Component
public class DataTableValidator extends AbstractMetheaValidator<DataTableBinder> {
    @Override
    public void validate(DataTableBinder binder, Map<String, String> errors) {
        rejectIfBlank(errors, "label.datatable.name", binder.getViewName(), "viewName", "Datatable Name");
        rejectIfBlank(errors, "label.datatable.column.name", binder.getLabelColumnHead(), "labelColumnHead", "Column Label");
        rejectIfBlank(errors, "label.datatable.attribute.name", binder.getColumnKey(), "columnKey", "Attribute Name");
        rejectIfBlank(errors, "label.datatable.allow.filter", binder.getAllowFilter(), "allowFilter", "Allow Filter");
        rejectIfBlank(errors, "label.datatable.seq", String.valueOf(binder.getSequence()), "sequence", "Column Seq");
        MCache.CACHE_META_DATA.remove(binder.getViewName().concat(MConstant.COLUMNS_KEY));
        MCache.CACHE_META_DATA.remove(binder.getViewName().concat(MConstant.COLUMNS_LABEL));
    }
}
