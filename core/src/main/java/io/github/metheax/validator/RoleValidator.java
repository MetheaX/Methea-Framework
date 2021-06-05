package io.github.metheax.validator;

import io.github.metheax.domain.binder.RoleBinder;
import io.github.metheax.validator.abs.AbstractMetheaValidator;
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
