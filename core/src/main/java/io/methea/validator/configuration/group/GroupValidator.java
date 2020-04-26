package io.methea.validator.configuration.group;

import io.methea.domain.basebinder.abs.AbstractMetheaBinder;
import io.methea.domain.configuration.group.dto.UserGroupBinder;
import io.methea.validator.abs.AbstractMetheaValidator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 26/04/2020
 */
@Component
public class GroupValidator extends AbstractMetheaValidator {

    @Override
    public void validate(AbstractMetheaBinder binder, Map errors) {
        UserGroupBinder obj = (UserGroupBinder) binder;
        rejectIfEmpty(errors, "label.group.name.empty", obj.getGroupName(), "groupName", "Group Name");
    }
}
