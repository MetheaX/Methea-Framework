package io.methea.validator.configuration.display;

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

    }
}
