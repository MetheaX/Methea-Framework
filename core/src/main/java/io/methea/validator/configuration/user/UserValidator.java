package io.methea.validator.configuration.user;

import io.methea.domain.configuration.user.dto.UserBinder;
import io.methea.domain.configuration.user.entity.TUser;
import io.methea.repository.configuration.group.UserGroupRepository;
import io.methea.repository.configuration.user.UserRepository;
import io.methea.validator.abs.AbstractMetheaValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 26/04/2020
 */
@Component
public class UserValidator extends AbstractMetheaValidator<UserBinder> {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository, UserGroupRepository userGroupRepository) {
        this.userRepository = userRepository;
        super.repository = userGroupRepository;
    }

    @Override
    public void validate(UserBinder binder, Map<String, String> errors) {
        rejectIfInvalidParent(errors, "label.user.group.invalid", binder.getGroupId(), "groupId", "Group Name");
        rejectIfBlank(errors, "label.user.username.blank", binder.getUsername(), "username", "Username");
        TUser user = userRepository.findByUsername(binder.getUsername());
        if(!ObjectUtils.isEmpty(user)){
            errors.put("username", "*Error: user already exist!!!");
        }
        rejectIfBlank(errors,"label.user.firstName.blank", binder.getFirstName(), "firstName", "Firstname");
        rejectIfBlank(errors,"label.user.lastName.blank", binder.getLastName(), "lastName", "Lastname");
        rejectIfBlank(errors,"label.user.phone.blank", binder.getPhone(), "phone", "Phone");
        rejectIfBlank(errors,"label.user.email.blank", binder.getEmail(), "email", "Email");
        rejectIfBlank(errors,"label.user.password.blank", binder.getPassword(), "password", "Password");
        rejectIfNotMatch(errors, "label.password.not.match.error", binder.getPassword(), binder.getConfirmPassword(),
                "password", "Password");

    }
}
