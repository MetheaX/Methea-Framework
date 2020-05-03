package io.methea.validator.configuration.role;

import io.methea.domain.configuration.role.dto.RoleBinder;
import io.methea.validator.abs.AbstractMetheaValidator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 02/05/2020
 */
@Component
public class RoleValidator extends AbstractMetheaValidator<RoleBinder> {

    @Override
    public void validate(RoleBinder binder, Map<String, String> errors) {
        rejectIfBlank(errors, "label.role.name", binder.getName(), "name", "Role name");
    }
}
