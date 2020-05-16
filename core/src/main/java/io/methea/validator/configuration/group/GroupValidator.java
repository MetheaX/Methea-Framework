package io.methea.validator.configuration.group;

import io.methea.domain.configuration.group.dto.UserGroupBinder;
import io.methea.repository.configuration.account.AccountRepository;
import io.methea.validator.abs.AbstractMetheaValidator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 26/04/2020
 */
@Component
public class GroupValidator extends AbstractMetheaValidator<UserGroupBinder> {

    public GroupValidator(AccountRepository accountRepository) {
       super.repository = accountRepository;
    }

    @Override
    public void validate(UserGroupBinder binder, Map<String, String> errors) {
        rejectIfBlank(errors, "label.group.name.blank", binder.getGroupName(), "groupName", "Group Name");
        rejectIfBlank(errors, "label.group.account.blank", binder.getAccountId(), "accountId", "Account Name");
        rejectIfInvalidParent(errors, "label.group.account.invalid", binder.getAccountId(), "accountId", "Account Name");
    }
}
