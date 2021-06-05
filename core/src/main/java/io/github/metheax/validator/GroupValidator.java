package io.github.metheax.validator;

import io.github.metheax.repository.AccountRepository;
import io.github.metheax.domain.binder.GroupBinder;
import io.github.metheax.validator.abs.AbstractMetheaValidator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 26/04/2020
 */
@Component
public class GroupValidator extends AbstractMetheaValidator<GroupBinder> {

    public GroupValidator(AccountRepository accountRepository) {
       super.repository = accountRepository;
    }

    @Override
    public void validate(GroupBinder binder, Map<String, String> errors) {
        rejectIfBlank(errors, "label.group.name.blank", binder.getGroupName(), "groupName", "Group Name");
        rejectIfBlank(errors, "label.group.account.blank", binder.getAccountId(), "accountId", "Account Name");
        rejectIfInvalidParent(errors, "label.group.account.invalid", binder.getAccountId(), "accountId", "Account Name");
    }
}
