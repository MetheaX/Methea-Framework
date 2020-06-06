package io.methea.validator.webservice.apibase;

import io.methea.domain.webservice.baseapi.dto.APIBaseBinder;
import io.methea.validator.abs.AbstractMetheaValidator;
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
