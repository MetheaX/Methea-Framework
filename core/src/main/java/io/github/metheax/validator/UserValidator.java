package io.github.metheax.validator;

import io.github.metheax.domain.binder.UserBinder;
import io.github.metheax.domain.entity.TUser;
import io.github.metheax.repository.GroupRepository;
import io.github.metheax.repository.UserRepository;
import io.github.metheax.utils.PrincipalUtils;
import io.github.metheax.validator.abs.AbstractMetheaValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

/**
 * Author : DKSilverX
 * Date : 26/04/2020
 */
@Component
public class UserValidator extends AbstractMetheaValidator<UserBinder> {

    private final UserRepository userRepository;
    private final HttpServletRequest request;

    public UserValidator(UserRepository userRepository, GroupRepository userGroupRepository, HttpServletRequest request) {
        this.userRepository = userRepository;
        super.repository = userGroupRepository;
        this.request = request;
    }

    @Override
    public void validate(UserBinder binder, Map<String, String> errors) {
        TUser user;
        switch (Optional.ofNullable(errors.get("validateType")).orElse(StringUtils.EMPTY)) {
            case "RST":
                rejectIfBlank(errors, "label.user.password.blank", binder.getPassword(), "password", "Password");
                rejectIfNotMatch(errors, "label.password.not.match.error", binder.getPassword(), binder.getConfirmPassword(),
                        "password", "Password");
                errors.remove("validateType");
                break;
            case "CHN":
                rejectIfBlank(errors, "label.user.password.blank", binder.getPassword(), "password", "Password");
                rejectIfNotMatch(errors, "label.password.not.match.error", binder.getPassword(), binder.getConfirmPassword(),
                        "password", "Password");
                user = userRepository.findByUsername(PrincipalUtils.getUserLoginId(request));
                if (!ObjectUtils.isEmpty(user)) {
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    binder.setId(user.getId());
                    if (encoder.matches(binder.getPassword(), user.getPassword())) {
                        errors.put("password", "*Error: new password and old password must be different!!!");
                    }
                }
                errors.remove("validateType");
                break;
            case "EDIT":
                rejectIfInvalidParent(errors, "label.user.group.invalid", binder.getGroupId(), "groupId", "Group Name");
                rejectIfBlank(errors, "label.user.username.blank", binder.getUsername(), "username", "Username");
                user = userRepository.findByUsername(binder.getUsername());
                if (ObjectUtils.isEmpty(user)) {
                    errors.put("username", "*Error: user not found!!!");
                }
                rejectIfBlank(errors, "label.user.firstName.blank", binder.getFirstName(), "firstName", "Firstname");
                rejectIfBlank(errors, "label.user.lastName.blank", binder.getLastName(), "lastName", "Lastname");
                rejectIfBlank(errors, "label.user.phone.blank", binder.getPhone(), "phone", "Phone");
                rejectIfBlank(errors, "label.user.email.blank", binder.getEmail(), "email", "Email");
                errors.remove("validateType");
                break;
            default:
                rejectIfInvalidParent(errors, "label.user.group.invalid", binder.getGroupId(), "groupId", "Group Name");
                rejectIfBlank(errors, "label.user.username.blank", binder.getUsername(), "username", "Username");
                user = userRepository.findByUsername(binder.getUsername());
                if (!ObjectUtils.isEmpty(user)) {
                    errors.put("username", "*Error: user already exist!!!");
                }
                rejectIfBlank(errors, "label.user.firstName.blank", binder.getFirstName(), "firstName", "Firstname");
                rejectIfBlank(errors, "label.user.lastName.blank", binder.getLastName(), "lastName", "Lastname");
                rejectIfBlank(errors, "label.user.phone.blank", binder.getPhone(), "phone", "Phone");
                rejectIfBlank(errors, "label.user.email.blank", binder.getEmail(), "email", "Email");
                rejectIfBlank(errors, "label.user.password.blank", binder.getPassword(), "password", "Password");
                rejectIfNotMatch(errors, "label.password.not.match.error", binder.getPassword(), binder.getConfirmPassword(),
                        "password", "Password");
                break;
        }
    }
}
