package io.methea.validator.configuration.uri;

import io.methea.domain.configuration.uri.dto.RoleURIBinder;
import io.methea.validator.abs.AbstractMetheaValidator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 16/05/2020
 */
@Component
public class RoleURIValidator extends AbstractMetheaValidator<RoleURIBinder> {

    @Override
    public void validate(RoleURIBinder binder, Map<String, String> errors) {
    }
}
