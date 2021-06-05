package io.github.metheax.validator;

import io.github.metheax.cache.MetheaCache;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.domain.binder.DataTableBinder;
import io.github.metheax.validator.abs.AbstractMetheaValidator;
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
        MetheaCache.CACHE_META_DATA.remove(binder.getViewName().concat(MetheaConstant.COLUMNS_KEY));
        MetheaCache.CACHE_META_DATA.remove(binder.getViewName().concat(MetheaConstant.COLUMNS_LABEL));
    }
}
