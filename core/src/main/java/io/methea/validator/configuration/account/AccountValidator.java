package io.methea.validator.configuration.account;

import io.methea.domain.basebinder.abs.AbstractMetheaBinder;
import io.methea.validator.abs.AbstractMetheaValidator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 26/04/2020
 */
@Component
public class AccountValidator extends AbstractMetheaValidator {
    @Override
    public void validate(AbstractMetheaBinder binder, Map errors) {
    }
}
