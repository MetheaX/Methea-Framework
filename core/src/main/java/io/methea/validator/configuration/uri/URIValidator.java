package io.methea.validator.configuration.uri;

import io.methea.domain.configuration.uri.dto.URIBinder;
import io.methea.validator.abs.AbstractMetheaValidator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 06/05/2020
 */
@Component
public class URIValidator extends AbstractMetheaValidator<URIBinder> {
    @Override
    public void validate(URIBinder binder, Map<String, String> errors) {
        rejectIfBlank(errors, "label.uri.name", binder.getUriName(), "uriName", "URI Name");
    }
}
