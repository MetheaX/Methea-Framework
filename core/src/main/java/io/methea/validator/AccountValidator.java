package io.methea.validator;

import io.methea.domain.binder.AccountBinder;
import io.methea.validator.abs.AbstractMetheaValidator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 26/04/2020
 */
@Component
public class AccountValidator extends AbstractMetheaValidator<AccountBinder> {
    @Override
    public void validate(AccountBinder binder, Map<String, String> errors) {
        rejectIfBlank(errors, "label.account.name.blank", binder.getAccountName(), "accountName", "Account Name");
        rejectIfBlank(errors, "label.account.email.blank", binder.getAccountEmail(), "accountEmail", "Account Email");
    }
}
