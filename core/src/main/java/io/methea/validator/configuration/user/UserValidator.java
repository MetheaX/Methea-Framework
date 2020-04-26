package io.methea.validator.configuration.user;

import io.methea.domain.basebinder.abs.AbstractMetheaBinder;
import io.methea.domain.configuration.user.dto.UserBinder;
import io.methea.validator.abs.AbstractMetheaValidator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 26/04/2020
 */
@Component
public class UserValidator extends AbstractMetheaValidator {

    @Override
    public void validate(AbstractMetheaBinder binder, Map errors) {
        UserBinder obj = (UserBinder) binder;
        rejectIfNotMatch(errors, "label.password.not.match.error", obj.getPassword(), obj.getConfirmPassword(),
                "password", "Password");
    }
}
