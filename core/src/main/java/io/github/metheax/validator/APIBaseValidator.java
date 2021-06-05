package io.github.metheax.validator;

import io.github.metheax.domain.binder.APIBaseBinder;
import io.github.metheax.validator.abs.AbstractMetheaValidator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 06/06/2020
 */
@Component
public class APIBaseValidator extends AbstractMetheaValidator<APIBaseBinder> {
    @Override
    public void validate(APIBaseBinder binder, Map<String, String> errors) {
        rejectIfBlank(errors, "label.api.url", binder.getApiUrl(), "apiUrl", "API URL");
    }
}
